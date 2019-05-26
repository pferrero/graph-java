package com.grafos.grafos;

import java.util.List;

public interface Graph {
	
	public int order();
	public int size();
	public int degree(int vertex); 
	public void addEdge(int from, int to);
	public void removeEdge(int from, int to);
	public void addVertex();
	public void removeVertex(int vertex);
	public List<Integer> neighbours(int vertex);

}
