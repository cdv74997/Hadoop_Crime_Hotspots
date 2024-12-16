import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class DBSCANDriver {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: DBSCANDriver <input path> <output path>");
            System.exit(-1);
        }

        // Set up job configuration
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "DBSCAN Clustering");

        // Set the driver, mapper, and reducer classes
        job.setJarByClass(DBSCANDriver.class);
        job.setMapperClass(DBSCANMapper.class);
        job.setReducerClass(DBSCANReducer.class);

        // Set the output key and value classes
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // Set input and output paths
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // Run the job
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
