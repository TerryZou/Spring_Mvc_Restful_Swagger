package com.meme.mymvc.restfulapi;

public class User {
	private int id;
	private String name;
	public void setId(int value)
	{
		this.id=value;
	}
	public int getId()
	{
		return this.id;
	}
	public void setName(String value)
	{
		this.name=value;
	}
	public  String getName()
	{
		return this.name;
	}
}
