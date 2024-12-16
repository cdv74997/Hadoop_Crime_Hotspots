package CrimeHotSpotsProject;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class HouseDBSCANDriver extends Configured implements Tool {

    public int run(String[] args) throws Exception {
        // setting job configuration for housing points
        JobConf jobConf = new JobConf(getConf(), HouseDBSCANDriver.class);
        jobConf.setJobName("Housing Clustering");

        // setting Mapper and Reducer
        jobConf.setMapperClass(HouseMapper.class);  
        jobConf.setReducerClass(HouseReducer.class);  

        // setting output key and value types
        jobConf.setOutputKeyClass(Text.class); 
        jobConf.setOutputValueClass(HousePoint.class);  

        // setting input and output formats
        jobConf.setInputFormat(TextInputFormat.class);
        jobConf.setOutputFormat(TextOutputFormat.class);

        // Set the map output key and value types
        jobConf.setMapOutputKeyClass(Text.class); 
        jobConf.setMapOutputValueClass(HousePoint.class); 

        // input and output path setting
        FileInputFormat.addInputPath(jobConf, new Path(args[0]));
        FileOutputFormat.setOutputPath(jobConf, new Path(args[1]));

        // and we run it to finish
        JobClient.runJob(jobConf);
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new HouseDBSCANDriver(), args);
        System.exit(res);
    }
}
