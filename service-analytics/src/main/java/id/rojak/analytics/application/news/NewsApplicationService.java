package id.rojak.analytics.application.news;

import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.Media;
import id.rojak.analytics.domain.model.media.MediaId;
import id.rojak.analytics.domain.model.media.MediaRepository;
import id.rojak.analytics.domain.model.news.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by imrenagi on 7/19/17.
 */
@Service
public class NewsApplicationService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Transactional
    public Page<News> allNewsBy(String mediaId,
                                String electionId,
                                Pageable pageRequest) {

        Page<News> news = this.newsRepository.findByMediaIdAndElectionId(
                new MediaId(mediaId),
                new ElectionId(electionId),
                pageRequest);

        return news;
    }

    @Transactional
    public Page<News> allNewsBy(String electionId,
                                Date fromDate,
                                Date toDate,
                                Pageable pageRequest) {

        Page<News> news = this.newsRepository.findByTimestampBetweenAndElectionId(
                fromDate,
                toDate,
                new ElectionId(electionId),
                pageRequest);

        return news;

    }

    @Transactional
    public News insertNews(String electionId,
                           String mediaId,
                           NewNewsCommand aCommand) {

        //TODO check electionId

        Media media = this.mediaRepository.findByMediaId(new MediaId(mediaId));

        if (media == null) {
            throw new IllegalArgumentException(String.format(
                    "Media %s is not exist",
                    mediaId));
        }

        News news = new News(
                new NewsId(this.newsRepository.nextId()),
                aCommand.getTitle(),
                aCommand.getUrl(),
                aCommand.getContent(),
                new MediaId(mediaId),
                new ElectionId(electionId),
                new Date(aCommand.getTimestamp()));

        news.insertTo(media);

        for (CandidateSentimentCommand sentiment : aCommand.getSentiments()) {

            NewsSentiment newsSentiment = new NewsSentiment(
                    new MediaId(mediaId),
                    new ElectionId((electionId)),
                    new CandidateId(sentiment.getCandidateId()),
                    new Date(aCommand.getTimestamp()),
                    SentimentType.valueOf(sentiment.getSentiment()));

            newsSentiment.insertTo(news);
        }

        return news;
    }

    @Transactional
    public News insertSentiments(String electionId,
                                 String mediaId,
                                 String newsId,
                                 InsertSentimentCommand command) {

        //TODO check electionId

        Media media = this.mediaRepository
                .findByMediaId(new MediaId(mediaId));

        if (media == null) {
            throw new IllegalArgumentException(String.format(
                    "Media %s is not exist",
                    mediaId));
        }

        News news = this.newsRepository
                .findByNewsId(new NewsId(newsId));

        //TODO check whether news is part of the media and election.

        for (CandidateSentimentCommand sentiment : command.sentiments()) {

            NewsSentiment newsSentiment = new NewsSentiment(
                    new MediaId(mediaId),
                    new ElectionId((electionId)),
                    new CandidateId(sentiment.getCandidateId()),
                    news.timestamp(),
                    SentimentType.valueOf(sentiment.getSentiment()));

            newsSentiment.insertTo(news);
        }

        return news;

    }
}
