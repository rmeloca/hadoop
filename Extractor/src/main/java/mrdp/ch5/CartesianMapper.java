/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrdp.ch5;

import info.debatty.java.stringsimilarity.Jaccard;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
 *
 * @author romulo
 */
public class CartesianMapper extends MapReduceBase implements Mapper<Text, Text, Text, Text> {

    private Text outkey = new Text();

    @Override
    public void map(Text key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {

        // If the two comments are not equal
        if (!key.toString().equals(value.toString())) {
            String tweet1 = key.toString().split(":")[1];
            String tweet2 = value.toString().split(":")[1];

            Jaccard jaccard = new Jaccard(2);
            double similarity = jaccard.similarity(tweet1, tweet2);
            if (similarity > 0.6 && similarity < 0.7) {
                System.out.println(tweet1);
                System.out.println(tweet2);
                System.out.println("Similarity:" + similarity);
                System.out.println("---");
                outkey.set(key);
                output.collect(outkey, value);
            }

//            String[] leftTokens = key.toString().split("\\s");
//            String[] rightTokens = value.toString().split("\\s");
//
//            HashSet<String> leftSet = new HashSet<String>(Arrays.asList(leftTokens));
//            HashSet<String> rightSet = new HashSet<String>(Arrays.asList(rightTokens));
//
//            int sameWordCount = 0;
//            StringBuilder words = new StringBuilder();
//            for (String s : leftSet) {
//                if (rightSet.contains(s)) {
//                    words.append(s + ",");
//                    ++sameWordCount;
//                }
//            }
//
//            if (sameWordCount > 2) {
//            }
        }
    }
}
