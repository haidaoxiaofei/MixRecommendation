package com.bigstone.evaluator;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.common.RandomUtils;

import com.bigstone.recommender.MyItemBasedRecommender;

public class EvaluateRecommender {

	/**
	 * @param args
	 * 
	 * Evaluator some recommendation methods based on their deviation and so on.
	 * @throws TasteException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws TasteException, IOException {
		RandomUtils.useTestSeed();
		Logger log = Logger.getLogger("lavasoft"); 
        log.setLevel(Level.INFO); 
        ConsoleHandler consoleHandler = new ConsoleHandler(); 
        consoleHandler.setLevel(Level.ALL); 
        log.addHandler(consoleHandler); 
        FileHandler fileHandler = new FileHandler("/media/bigstone/MyFiles/datasets/nf_prize/scripts/test.log"); 
        fileHandler.setLevel(Level.INFO); 
        fileHandler.setFormatter(new MyLogHander()); 
        log.addHandler(fileHandler); 

        
        
		DataModel model = new FileDataModel(new File("/media/bigstone/MyFiles/datasets/nf_prize/scripts/userBasedSet"));//构造数据模型，Database-based  
		RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
//		MyUserBasedRecommender r = new MyUserBasedRecommender();
		MyItemBasedRecommender r = new MyItemBasedRecommender();
		double score = 0;
		for(int i = 0; i < 30; i++){
			score = evaluator.evaluate(r, null, model, 0.7+i*0.01, 1.0);
			log.info(Double.toString(score));
		}
	}

}
class MyLogHander extends Formatter { 
    @Override 
    public String format(LogRecord record) { 
            return record.getLevel() + ":" + record.getMessage()+"\n"; 
    } 
}