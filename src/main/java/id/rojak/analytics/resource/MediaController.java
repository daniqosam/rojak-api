package id.rojak.analytics.resource;

import id.rojak.analytics.application.media.MediaApplicationService;
import id.rojak.analytics.application.media.NewMediaCommand;
import id.rojak.analytics.application.media.UpdateMediaCommand;
import id.rojak.analytics.domain.model.media.Media;
import id.rojak.analytics.resource.dto.MediaCollectionDTO;
import id.rojak.analytics.resource.dto.MediaDTO;
import id.rojak.analytics.resource.dto.MetaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by imrenagi on 7/14/17.
 */
@RestController
@RequestMapping("/medias")
public class MediaController {

    @Autowired
    MediaApplicationService mediaApplicationService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MediaCollectionDTO> getAllMedias(
            @RequestParam(value="page", defaultValue = "0") Integer page,
            @RequestParam(value="limit", defaultValue="10") Integer size) {

        Pageable pageRequest = new PageRequest(page, size);

        Page<Media> medias = this.mediaApplicationService
                .allMedias(pageRequest);

        List<MediaDTO> mediaDTOs = medias.getContent().stream()
                .map(media -> new MediaDTO(media.mediaId().id(),
                        media.name(),
                        media.websiteUrl(),
                        media.logo())
                ).collect(Collectors.toList());

        return new ResponseEntity<>(
            new MediaCollectionDTO(mediaDTOs,
                    new MetaDTO(page,
                            medias.getTotalPages(),
                            medias.getTotalElements())), HttpStatus.OK);

    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<MediaDTO> createNewMedia(@Valid @RequestBody NewMediaCommand command) {

        Media media = this.mediaApplicationService.newMedia(command);

        MediaDTO mediaDTO = new MediaDTO(media.mediaId().id(),
                media.name(),
                media.websiteUrl(),
                media.logo());

        return new ResponseEntity<>(mediaDTO, HttpStatus.OK);
    }

    @RequestMapping(path = "/{media_id}", method = RequestMethod.PUT)
    public ResponseEntity<MediaDTO> updateMedia(@PathVariable("media_id") String aMediaId,
                                                @Valid @RequestBody UpdateMediaCommand command) {

        Media media = this.mediaApplicationService.updateMedia(aMediaId, command);

        MediaDTO mediaDTO = new MediaDTO(media.mediaId().id(),
                media.name(),
                media.websiteUrl(),
                media.logo());

        return new ResponseEntity<>(mediaDTO, HttpStatus.OK);
    }

    @RequestMapping(path = "/{media_id}", method = RequestMethod.GET)
    public ResponseEntity<MediaDTO> mediaDetail(@PathVariable("media_id") String aMediaId) {

        Media media = this.mediaApplicationService.media(aMediaId);

        MediaDTO mediaDTO = new MediaDTO(media.mediaId().id(),
                media.name(),
                media.websiteUrl(),
                media.logo());

        return new ResponseEntity<>(mediaDTO, HttpStatus.OK);
    }

    @RequestMapping(path="/{media_id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeMedia(@PathVariable("media_id") String aMediaId) {

        this.mediaApplicationService.remove(aMediaId);

        return new ResponseEntity<>("", HttpStatus.OK);

    }

}
