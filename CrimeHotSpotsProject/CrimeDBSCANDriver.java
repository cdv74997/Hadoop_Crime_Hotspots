package CrimeHotSpotsProject;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class CrimeDBSCANDriver extends Configured implements Tool {

    public int run(String[] args) throws Exception {
        // setting job configuration
        JobConf jobConf = new JobConf(getConf(), CrimeDBSCANDriver.class);
        jobConf.setJobName("Crime Hot Spots");

        // setting Mapper, Reducer, and other job configurations
        jobConf.setMapperClass(CrimeMapper.class);
        jobConf.setReducerClass(CrimeReducer.class);

        // setting output key and value types
        jobConf.setOutputKeyClass(Text.class); 
        jobConf.setOutputValueClass(CrimePoint.class); 

        // setting input and output formats
        jobConf.setInputFormat(TextInputFormat.class);
        jobConf.setOutputFormat(TextOutputFormat.class);

        // setting map output key and value types
        jobConf.setMapOutputKeyClass(Text.class); 
        jobConf.setMapOutputValueClass(CrimePoint.class); 

        // input and output paths 
        FileInputFormat.addInputPath(jobConf, new Path(args[0]));
        FileOutputFormat.setOutputPath(jobConf, new Path(args[1]));

        // finally, we run the job
        JobClient.runJob(jobConf);
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new CrimeDBSCANDriver(), args);
        System.exit(res);
    }
}
