package com.extractor;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<Text, Iterable<Text>, Text, Text> {

//    private IntWritable result = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<Iterable<Text>> values, Context context) throws IOException, InterruptedException {
//        int sum = 0;
//        for (IntWritable val : values) {
//            sum += val.get();
//        }
//        result.set(sum);
//        context.write(key, result);
    }
}
