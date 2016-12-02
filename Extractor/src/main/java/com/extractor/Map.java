package com.extractor;

import info.debatty.java.stringsimilarity.Jaccard;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<Object, Iterable<Text>, Text, Iterable<Text>> {

    Text tweet = null;
    IntWritable distance = null;

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    @Override
    protected void map(Object key, Iterable<Text> value, Context context) throws IOException, InterruptedException {
        Jaccard jaccard = new Jaccard(2);
        this.tweet = value.iterator().next();
        System.out.println(tweet.toString());
        this.distance = new IntWritable((int) jaccard.similarity("ABCDE", "ABCDF"));
        context.write(tweet, new ArrayList<>());
    }
}
