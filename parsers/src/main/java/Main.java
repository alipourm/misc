import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.simple.*;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;


import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Main {


        public static String text1 = "Joe Smith was born in California. " +
                "In 2017, he went to Paris, France in the summer. " +
                "His flight left at 3:00pm on July 10th, 2017. " +
                "After eating some escargot for the first time, Joe said, \"That was delicious!\" " +
                "He sent a postcard to his sister Jane Smith. " +
                "After hearing about Joe's trip, Jane decided she might go to France one day.";
        public static String text2 = "Have you found the answer for your question? If yes would you please share it?";

        public static void main(String[] args) {

            // set up pipeline properties
            Properties props = new Properties();
            // set the list of annotators to run
//            props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,coref,kbp,quote");
            props.setProperty("annotators", "tokenize,ssplit,pos,depparse");

            // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
//            props.setProperty("coref.algorithm", "neural");
            // build pipeline

            long start = System.currentTimeMillis();
            StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            System.out.println("initialization" + timeElapsed);

            String[] texts = {text1,text2};

             start =System.currentTimeMillis();

            // create a document object
            for (String text: texts){
                CoreDocument document = new CoreDocument(text);
                // annnotate the document
                pipeline.annotate(document);
                // examples

                // 10th token of the document

                for (CoreSentence sentence: document.sentences()){
                    String sentenceText = sentence.text();
                    System.out.println(sentenceText);
                    SemanticGraph dependencyParse = sentence.dependencyParse();
                    System.out.println("dependency parse");
                    System.out.println(dependencyParse);
                    System.out.println(dependencyParse.typedDependencies());

                }

            }
            finish =System.currentTimeMillis();
            timeElapsed = finish - start;
            System.out.println("resr" + timeElapsed);


    }
}
