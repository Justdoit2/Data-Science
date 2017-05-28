/**
 * STUDENT: YOU HAVE TO MODIFY THIS CODE. PLEASE MAKE SURE YOU DO NOT 
 * CHANGE THE PUBLIC METHOD HEADERS. FEEL FREE TO ADD YOUR OWN PRIVATE 
 * METHODS IN THE CODE AS NEEDED.
 
 * COMPLETE THE **TODO** STUBS IN THIS CODE
 */


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class KNN {
	// Find K nearest neighbors of testData within trainingSet and then
    // classify to a label.
	static int findKNearestNeighbors(TrainData[] trainingSet,
                                   TestData testData,
                                   int K, String metric_type) {
		int NumOfTrainingSet = trainingSet.length;
		assert K <= NumOfTrainingSet : "K is lager than the length of trainingSet!";
		
		// Update KNN: take the case when testData has multiple neighbors with the
        // same distance into consideration
		
        //Solution: Update the size of container holding the neighbors
		TrainData[] neighbors = new TrainData[K];
          int tracker;
          for ( tracker=0; tracker<K;tracker++){
      trainingSet[tracker].distance=getDistance(trainingSet[tracker],testData,metric_type);
                  neighbors[tracker]=trainingSet[tracker];
		}
          
        for ( tracker=K; tracker<NumOfTrainingSet;++tracker){
             trainingSet[tracker].distance=getDistance(trainingSet[tracker],testData,metric_type);
            int Biggest=0;
               for ( int i =1; i<K;++i){
                       if (neighbors[i].distance>neighbors[Biggest].distance) Biggest=i;



 }
       if (neighbors[Biggest].distance>trainingSet[tracker].distance) {
            neighbors[Biggest]=trainingSet[tracker];
}
      

}
		
		return classify(neighbors);
	}
	
	// Get the class label by using neighbors
	static int classify(TrainData[] neighbors) {
		//construct a HashMap to store <classLabel, weight>
		int K = neighbors.length;
                double more;       
                  HashMap<Integer, Double>map= new HashMap<Integer,Double>();
         for ( int i=0;i<neighbors.length;++i){
             TrainData present=neighbors[i]; //value for data
             int type= present.classLabel; //label for class (get value, then classLabel for value)
                 if(!map.containsKey(neighbors[i].classLabel)){
                    map.put(neighbors[i].classLabel,1/neighbors[i].distance);     
		}
                else {
                   more=map.get(neighbors[i].classLabel);
                   more+=1/neighbors[i].distance;
                    map.put(neighbors[i].classLabel,more);
}

}
        
       double maxSame=0;
       
        int labelWithMaxSimilarity = -1;
         Set<Integer>mySet=map.keySet(); // intialize set
         Iterator<Integer>it=mySet.iterator(); //for looping through
         while(it.hasNext()){
            int now=it.next(); //extract the value
            double val = map.get(now); //it matters to extract the value instead of getting it all at once with map.get(it.next()) does not work well
             if (val>maxSame){
                     maxSame=val;
                    labelWithMaxSimilarity=now;
}
            
}
		
		return labelWithMaxSimilarity;
	}

  static double getDistance(Data s, Data e, String metric_type) {
    if (metric_type.equals("cosine") || metric_type.equals("Cosine")) {
      return getCosineDistance(s, e);
    } else if (metric_type.equals("L1") || metric_type.equals("l1")) {
      return getL1Distance(s, e);
    } else if (metric_type.equals("L2") || metric_type.equals("l2") ||
               metric_type.equals("euclidean") || metric_type.equals("Euclidean")) {
      return getEuclideanDistance(s, e);
    }
    System.out.println("Invalid metric type: " + metric_type +
                       " returning MAX_VALUE");
    return Double.MAX_VALUE;
  }
    // Cosine Distance
	static double getCosineDistance(Data s, Data e) {
        // Feel free to implemenet & test if you want. You will NOT be graded for
        // this one. Returning MAX_VALUE to make this code compile.
        // return Double.MAX_VALUE;
        
        return Double.MAX_VALUE; // You need to change this if you implement
 	}
    
    // Euclidean distance
	static double getEuclideanDistance(Data s, Data e) {
		assert s.attributes.length == e.attributes.length : "s and e are different types of records!";
		int numOfAttributes = s.attributes.length;
		double sum2 = 0;
		
		for(int i = 0; i < numOfAttributes; i ++){
			sum2 += Math.pow(s.attributes[i] - e.attributes[i], 2);
		}
		
		return Math.sqrt(sum2);
	}
    
    // L1 diatance
	static double getL1Distance(Data s, Data e) {
        // Feel free to implemenet & test if you want. You will NOT be graded for
        // this one. Returning MAX_VALUE to make this code compile.
        // return Double.MAX_VALUE;
		
		return Double.MAX_VALUE; // You need to change this if you implement
	}
}
