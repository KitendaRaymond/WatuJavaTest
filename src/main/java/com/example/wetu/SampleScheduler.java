package com.example.wetu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Configuration
public class SampleScheduler
{

    @Value("${runTimeMilli}")
    private static String runTimemills;

    private static final Logger logger = LoggerFactory.getLogger(SampleScheduler.class);

   // @Scheduled(fixedDelay = 60000)
    public void processFile()
    {
        boolean runOperation = false;
        String line = "";
        String splitBy = ",";

        try
        {
            //READ FILED AND PARSE DATA
            String filename = "documents/operations.csv";
            BufferedReader br = new BufferedReader(new FileReader(filename));

            while ((line = br.readLine()) != null)
            {
                try
                {
                    String[] operations = line.split(splitBy);
                    String time = operations[0];
                    String dayToOperate = operations[1];
                    String dayNameToOperate = "";

                    //Get the hour and minute details of the cell
                    String[] timeDetails = time.split(":");
                    String hourToOperate = timeDetails[0];
                    String minuteToOperate = timeDetails[1];

                    if(dayToOperate.equals("0x00"))
                    {
                        dayNameToOperate = "Sunday";
                    }
                    else if(dayToOperate.equals("0x01"))
                    {
                        dayNameToOperate = "Monday";
                    }
                    else if(dayToOperate.equals("0x10"))
                    {
                        dayNameToOperate = "Tuesday";
                    }
                    else if(dayToOperate.equals("0x11"))
                    {
                        dayNameToOperate = "Wednesday";
                    }
                    else if(dayToOperate.equals("1x00"))
                    {
                        dayNameToOperate = "Thursday";
                    }
                    else if(dayToOperate.equals("1x01"))
                    {
                        dayNameToOperate = "Friday";
                    }
                    else if(dayToOperate.equals("1x10"))
                    {
                        dayNameToOperate = "Saturday";
                    }
                    else
                    {
                        dayNameToOperate = "";
                    }

                    Instant now = Instant.now();
                    ZonedDateTime zdt = ZonedDateTime.ofInstant(now, ZoneId.of("Africa/Lagos"));

                    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

                    DayOfWeek dayOfWeek = DayOfWeek.from(zdt);

                    //DETERMINE IF IT IS THE DAY CONFIGURED IN THE FILE
                    if(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()).toUpperCase().equals(dayNameToOperate.toUpperCase()))
                    {
                        //IF TODAY IS THE RIGHT DAY THEN CHECK IF IT IS THE RIGHT HOUR

                        if(zdt.getHour() == Integer.valueOf(hourToOperate))
                        {
                            //IT IS THE RIGHT HOUR...CHECK IF IT IS THE RIGHT MINUTE

                            if(zdt.getMinute() == Integer.valueOf(minuteToOperate))
                            {
                                runOperation = true;
                            }
                            else
                            {
                                runOperation = false;
                            }
                        }
                        else
                        {
                            runOperation = false;
                        }

                    }
                    else
                    {
                        runOperation = false;
                    }

                }
                catch (Exception ex)
                {
                    logger.error("EXCEPTION : "+ex.getMessage());

                    runOperation = false;
                }

                if (runOperation)
                {
                    //CARRY OUT THE OPERATION YOU WANT...
                    logger.info("OPERATION HAS RAN AT THE RIGHT TIME!!!");
                }
            }
        }
        catch (Exception ex)
        {
            logger.error("EXCEPTION : "+ex.getMessage());
        }



    }
}
