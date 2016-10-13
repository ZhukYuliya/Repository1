package by.training.task2.entity;

import java.util.ArrayList;

import by.training.task2.factory.TrainType;

public class Train extends ArrayList<Carriage> {

	private String name;
	private TrainType trainType;

	public TrainType getTrainType() {
		return trainType;
	}

	public void setTrainType(TrainType trainType) {
		this.trainType = trainType;
	}

	public Train(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private static final long serialVersionUID = 1L;

}
