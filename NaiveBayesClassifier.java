// Copyright 2017 smanna@scu.edu
// STUDENT: Feel free to modify this file. As long as you do not 
// change the public method headers, it should be ok.
// You can use your own private member variables or methods

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.*;

public class NaiveBayesClassifier {
  // total number of documents added to the classifier
  private int totalDocumentCount;

  // Set of all unique tokens ever encountered => vocabulary
  private TreeSet<String> vocab; //like set, but for large documents and fast

  // An inner class used to maintain all the stats per category being provided
  // for convenience.
  // STUDENTS: Feel free to add/remove more data if you want. It is also
  // allowed completely avoiding use of CategoryStats.
  public class CategoryStats {
    // total number of documents in this category
    public int documentsInCategory;

    // TreeMap for the term frequencies of each token in the category
    public TreeMap<String, Integer> categoryTF;

    // each document (within this category's) term frequencies
    public ArrayList<TreeMap<String, Integer>> documentTF;

    public CategoryStats() {
      documentsInCategory = 0;
      categoryTF = new TreeMap<>();
      documentTF = new ArrayList<>();
    }
  }

  // data maps each category name to its stats.
  // STUDENT: If you use the above CategoryStats, you may need this. But it is
  // absolutely fine to comment it out.
  private TreeMap<String, CategoryStats> data;
        
  // Constructor
  public NaiveBayesClassifier() {
    totalDocumentCount = 0;
    vocab = new TreeSet<>();
    data = new TreeMap<>();
  }

  // Training phase
  public void trainDocument(String doc, String cat) {
    // increment the total document count
    totalDocumentCount++; //has a bunch of documents, up to 3000
 

    
   // System.out.println(doc); //has spaces, periods, and explanation marks

     //removes punctuation, switch to lowercase, stem, and split the document on white space
    String[] tokens = clean(doc); 
     
// add this category to the data field if it doesn't exist
    // STUDENTS: Feel free to comment it if you do not use.
     //cat: fitntess, econ, askengineers, ama
     //data= as all the category of cat
    if (!data.containsKey(cat)) data.put(cat, new CategoryStats());
       
    CategoryStats currentCat = data.get(cat);
          
       
     

    // TODO(STUDENTS): Implement your own datastructure to store learnt model
    // and update based on current input.
    
    for ( int i =0; i< tokens.length;++i)
 {      int helloItsMe;
       if (currentCat.categoryTF.containsKey(tokens[i])) 
	{
	helloItsMe=currentCat.categoryTF.get(tokens[i]) +1;
        currentCat.categoryTF.put(tokens[i],helloItsMe);
	}
       else
	{
         currentCat.categoryTF.put(tokens[i],1);
	}
                
}
       

  }

  public String classify(String doc) {
    // clean, stem, and vectorize the input test data document
    String[] tokens = clean(doc);
   
    String answer = "";
    // TODO(STUDENTS): Using given tokens and your already computed
    // data-structure, try to predict the category and return.
   int count=0;
 


 double max=0;
  // Arraylist al= new ArrayList();
   
     for (String category:data.keySet()) //all independent categories
{            
             
     CategoryStats currentCat = data.get(category); //obtain category, individual category
      double tracker=0;
      vocab.clear();
       for ( int p = 0; p<tokens.length;++p)
   {
                  vocab.add(tokens[p]);
                   
       }
         int uniquevocab=vocab.size();
               
        System.out.println(uniquevocab);
       for ( int i =0; i< tokens.length;++i)
       {    
             //System.out.println(vocab.size());
         String mytokes=tokens[i];
         if(currentCat.categoryTF.containsKey(mytokes))
		{
 			int incre=currentCat.categoryTF.get(mytokes);
                        //double result=tokens.length+uniquevocab;
 			
                        tracker+=(double)(incre+1)/(tokens.length+uniquevocab);
                         // tracker+=(double)incre +1/;
                         
		}    
                 

}
    
     if (tracker>max)
      {
	max=tracker;
       
        answer=category;
       } 
      
        
}
    

   
    return answer;
  }

  // cleans and stems each string, and gets rid of stop words
  private String[] clean(String doc) {
    String[] titles = doc.toLowerCase().replaceAll("[^\\w ]", "").split("\\s+");
    StopWords stop = new StopWords();
    ArrayList<String> holder = new ArrayList<>();
    for (int i = 0; i < titles.length; i++) {
      if (stop.isStopWord(titles[i])) {
        titles[i] = null;
        continue;
      }
      Stemmer s = new Stemmer();
      char[] word = titles[i].toCharArray();
      s.add(word, word.length);
      s.stem();
      holder.add(s.toString());
    }

    titles = holder.toArray(new String[holder.size()]);
    return titles;
  }
}
