/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrdp.ch5;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/**
 *
 * @author romulo
 */
public class CartesianReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

    @Override
    public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        System.out.println();
        System.out.print("chave: ");
        System.out.println(key.toString());
        System.out.println("valores: ");
        Collection<String> valores = new HashSet<>();
        while (values.hasNext()) {
            Text next = values.next();
            valores.add(next.toString());
        }
        for (String valor : valores) {
            System.out.println(valor);
        }
        System.out.println();
        output.collect(key, new Text(valores.toString()));
    }

}
