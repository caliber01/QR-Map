package com.example.qr_map.Logic;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class LocalHelper {
	//history
	public static Stack<String> read(String fileName)
	{
		Stack<String> st = null;
		try{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
		st = (Stack<String>) ois.readObject();
		}
		catch(Exception e)
		{
			st = new Stack<String>();
		}
		return st;
	}
	public static void write(String fileName,Stack<String> st)throws Exception
	{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
		oos.writeObject(st);
	}
	public static void add(String fileName,String value)throws Exception
	{
		Stack<String> st = read(fileName);
		if(!st.empty())
		{
			String s = st.peek();
			if(s.equals(value))
				return;
		}
		st.push(value);
		write(fileName,st);
	}
	
	//favorites
	public static PriorityQueue readQueue (String fileName)
	{
		PriorityQueue q = null;
		try{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
		q = (PriorityQueue) ois.readObject();
		}
		catch(Exception e)
		{
			q = new PriorityQueue();
		}
		return q;
	}
	public static void writeQueue(String fileName,PriorityQueue q)throws Exception
	{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
		oos.writeObject(q);
	}
	public static void addToQueue(String fileName,String value)throws Exception
	{
		PriorityQueue q = readQueue(fileName);
		if(!q.isEmpty())
		{
			if(q.contains(value))
				return;
		}
		q.add(value);
		writeQueue(fileName,q);
	}

}
