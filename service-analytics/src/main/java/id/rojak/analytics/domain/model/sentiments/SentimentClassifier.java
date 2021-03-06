package id.rojak.analytics.domain.model.sentiments;

import id.rojak.analytics.domain.model.news.SentimentType;

/**
 * Created by inagi on 7/20/17.
 */
public interface SentimentClassifier {

    SentimentType calculate(Long nPositive, Long nNegative, Long nNeutral);

}
