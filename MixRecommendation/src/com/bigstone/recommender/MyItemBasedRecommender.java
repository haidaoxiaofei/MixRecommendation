package com.bigstone.recommender;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public class MyItemBasedRecommender implements RecommenderBuilder{  
    public List<RecommendedItem> myItemBasedRecommender(DataModel model, long userID,int size){  
        List<RecommendedItem> recommendations = null;  
        try {  
            
            ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);//计算内容相似度  
            Recommender recommender = new GenericItemBasedRecommender(model, similarity);//构造推荐引擎  
            recommendations = recommender.recommend(userID, size);//得到推荐接过  
        } catch (Exception e) {  
            // TODO: handle exception  
            e.printStackTrace();  
        }  
        return recommendations;  
    }

	@Override
	public Recommender buildRecommender(DataModel model) throws TasteException {
		ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);//计算内容相似度  
        Recommender recommender = new CachingRecommender(new GenericItemBasedRecommender(model, similarity));//构造推荐引擎  
		return recommender;
	}  
}  