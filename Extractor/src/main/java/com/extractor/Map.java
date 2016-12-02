package com.extractor;

import info.debatty.java.stringsimilarity.Jaccard;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<Object, Text, Text, IntWritable> {

    Text tweet1 = null;
    Text tweet2 = null;
    IntWritable distance = null;

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        Jaccard jaccard = new Jaccard(2);
        String[] split = value.toString().split(":");
        String tweet = split[1];

        if (tweet1 != null && tweet2 != null && distance != null) {
            System.out.println(tweet);
            jaccard.similarity("ABCDE", "ABCDF");
            this.tweet1 = new Text(split[1]);
            this.distance = new IntWritable(Integer.valueOf(split[2]));
            context.write(new Text(tweet1 + "\t" + tweet2), distance);
            tweet1 = null;
            tweet2 = null;
            distance = null;
        }
    }
}
