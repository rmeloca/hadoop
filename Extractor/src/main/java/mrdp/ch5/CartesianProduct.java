package mrdp.ch5;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

@SuppressWarnings({"rawtypes", "static-access", "unchecked"})
public class CartesianProduct {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
//        long start = System.currentTimeMillis();
        JobConf conf = new JobConf("Similarity Tweets check");
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2) {
            System.err.println("Usage: CartesianProduct <comment data> <out>");
            System.exit(1);
        }

        // Configure the join type
        conf.setJarByClass(CartesianProduct.class);

        conf.setMapperClass(CartesianMapper.class);

        conf.setNumReduceTasks(0);

        conf.setInputFormat(CartesianInputFormat.class);
        CartesianInputFormat.setLeftInputInfo(conf, TextInputFormat.class, otherArgs[0]);
        CartesianInputFormat.setRightInputInfo(conf, TextInputFormat.class, otherArgs[0]);
        TextOutputFormat.setOutputPath(conf, new Path(otherArgs[1]));

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);

        RunningJob job = JobClient.runJob(conf);
//        while (!job.isComplete()) {
//            Thread.sleep(1000);
//        }

//        long finish = System.currentTimeMillis();
//        System.out.println("Time in ms: " + (finish - start));
//        System.exit(job.isSuccessful() ? 0 : 2);
    }

}
