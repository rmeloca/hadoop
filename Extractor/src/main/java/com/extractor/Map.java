package com.extractor;


import info.debatty.java.stringsimilarity.Jaccard;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author romulo
 */
public class Map extends Mapper<Text, Text, Text, Text> {

    private Text outkey = new Text();

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {

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
            }

        }
    }
}
