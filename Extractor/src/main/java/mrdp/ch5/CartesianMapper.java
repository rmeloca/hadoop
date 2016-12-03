/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrdp.ch5;

import info.debatty.java.stringsimilarity.Jaccard;
import java.io.IOException;
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

        if (!key.toString().equals(value.toString())) {
            try {
                String tweet1 = key.toString().split("\",\"")[0];
                String tweet2 = value.toString().split("\",\"")[0];
                tweet1 = tweet1.split("\":\"")[1];
                tweet2 = tweet2.split("\":\"")[1];
//            String tweet1 = key.toString();
//            String tweet2 = value.toString();

                Jaccard jaccard = new Jaccard(10);
                double similarity = jaccard.similarity(tweet1, tweet2);
                if (similarity > 0.6 && similarity < 0.7) {
                    System.out.println(tweet1);
                    System.out.println(tweet2);
                    System.out.println("Similarity:" + similarity);
                    System.out.println("---");
                    outkey.set(key);
                    output.collect(outkey, value);
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.err.println("Formato de entrada invalido");
            }
        }
    }
}
