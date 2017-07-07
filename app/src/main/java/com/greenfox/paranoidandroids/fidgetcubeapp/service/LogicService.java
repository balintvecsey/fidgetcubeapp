package com.greenfox.paranoidandroids.fidgetcubeapp.service;

public class LogicService {

  public int randomNumber(int min, int max) {
    return (int) (Math.random() * max) + min;
  }
}
