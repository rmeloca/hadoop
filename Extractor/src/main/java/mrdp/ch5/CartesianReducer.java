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
    public void reduce(Text k2, Iterator<Text> itrtr, OutputCollector<Text, Text> oc, Reporter rprtr) throws IOException {
        System.out.println("novo reduce");
        System.out.println("chave");
        System.out.println(k2.toString());
        System.out.println("valores");
        Collection<String> valores = new HashSet<>();
        while (itrtr.hasNext()) {
            Text next = itrtr.next();
            valores.add(next.toString());
        }
        for (String valor : valores) {
            System.out.println(valor);
        }
        System.out.println("=======");

//        StringBuilder resultadoChave = new StringBuilder();
//        resultadoChave.append("{");
//        resultadoChave.append("\"text\"");
//        resultadoChave.append(":");
//        resultadoChave.append("\"");
//        resultadoChave.append(k2.toString());
//        resultadoChave.append("\"");
//
//        StringBuilder resultadoValor = new StringBuilder();
//        resultadoValor.append(",");
//        resultadoValor.append("\"similar\"");
//        resultadoValor.append(":");
//        resultadoValor.append("[");
//        for (String valor : valores) {
//            resultadoValor.append("\"");
//            resultadoValor.append(valor);
//            resultadoValor.append("\"");
//            resultadoValor.append(",");
//        }
//        resultadoValor.append("]");
//        resultadoValor.append("}");
//        oc.collect(new Text(resultadoChave.toString()), new Text(resultadoValor.toString()));
        oc.collect(k2, new Text(valores.toString()));
    }

}
