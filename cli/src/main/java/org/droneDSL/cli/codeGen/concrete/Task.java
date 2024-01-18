package org.droneDSL.cli.codeGen.concrete;

import kala.collection.immutable.ImmutableSeq;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class Task {
  @Nullable public String wayPointsID;
  @Nullable public ImmutableSeq<Point> wayPoints;

  public String taskID;

  public Task(String taskID, @Nullable ImmutableSeq<Point> wayPoints, @Nullable String wayPointsID) {
    this.taskID = taskID;
    this.wayPoints = wayPoints;
    this.wayPointsID  = wayPointsID;
    this.transitions = new ArrayList<>();
  }

  public List<Transition> transitions;



  public record Point(float x, float y, float z) {
    public String toJson() {
      return String.format("{'lng': %s, 'lat': %s, 'alt': %s}", x, y, z);
    }
  }


  public record Transition<T>(
      String condID,
      @Nullable T condArg,
      String currentTaskID,
      String nextTaskID
  ){}

  public abstract void debugPrint();
  public abstract String generateDefineTaskCode(boolean isSteelEagle);
}
