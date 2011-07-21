// Copyright 2011 Google Inc. All Rights Reserved.
package com.google.wireless.speed.speedometer;


import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * MeasurementDesc and all its subclasses are POJO classes that encode a measurement 
 * and enable easy (de)serialization. On the other hand {@link MeasurementTask} contains 
 * runtime specific information for task execution. 
 * @author wenjiezeng@google.com (Steve Zeng)
 * @see MeasurementTask 
 */
public abstract class MeasurementDesc {
  
  // General parameters that are shared by all measurements
  public String type;
  public String key;
  public Date startTime;
  public Date endTime;
  public double intervalSec;
  public long count;
  public long priority;  
  public Map<String, String> parameters;
    
  /**
   * @param type Type of measurement (ping, dns, traceroute, etc.) 
   * that should execute this measurement task.
   * @param startTime Earliest time that measurements can be taken using this Task descriptor.
   * @param endTime Latest time that measurements can be taken using this Task descriptor.
   * @param intervalSec Minimum number of seconds to elapse between consecutive measurements taken 
   * with this description.
   * @param count Maximum number of times that a measurement should be taken with this 
   * description. A count of 0 means to continue the measurement indefinitely (until end_time).
   * @param priority Larger values represent higher priorities.
   * @param params Measurement parameters Measurement parameters.
   */
  protected MeasurementDesc(String type, String key, Date startTime, 
                            Date endTime, double intervalSec, long count, long priority, 
                            Map<String, String> params) {
    super();
    this.type = type;
    this.key = key;
    if (startTime == null) {
      this.startTime = Calendar.getInstance().getTime();
    } else {
      this.startTime = startTime;
    }
    this.endTime = endTime;
    this.intervalSec = intervalSec;
    this.count = count;
    this.priority = priority;
    this.parameters = params;
  }
  
  /** Return the type of the measurement (DNS, Ping, Traceroute, etc.)*/
  public abstract String getType();
  
  /** Subclass override this method to initialize measurement specific parameters*/
  protected abstract void initalizeParams(Map<String, String> params);
  
  @Override
  public String toString() {
    return "<MeasurementTask> " + this.type + " deadline:" + endTime +
      " params:" + parameters;
  }  
}