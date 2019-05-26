package com.grafos.grafos;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraph implements Graph {
	
	private boolean[][] edges;
	private int order;
	private int size;
	
	public UndirectedGraph(int vertices) {
		if( vertices < 1 )
			throw new IllegalArgumentException("Vertices must be greater than 0. Vertices = " + vertices);
		this.edges = new boolean[vertices][vertices];
		this.order = vertices;
		this.size = 0;
	}

	@Override
	public int order() {
		return this.order;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public int degree(int vertex) {
		isVertex(vertex);
		return neighbours(vertex).size();
	}

	@Override
	public void addEdge(int nodeA, int nodeB) {
		isVertex(nodeA);
		isVertex(nodeB);
		this.edges[nodeA][nodeB] = true;
		this.edges[nodeB][nodeA] = true;
		this.size++;
	}
	
	@Override
	public void removeEdge(int nodeA, int nodeB) {
		isVertex(nodeA);
		isVertex(nodeB);
		if( !checkEdge(nodeA, nodeB) )
			throw new IllegalArgumentException("This edge does not exist. Edge = (" + nodeA + "," + nodeB + ")");
		this.edges[nodeA][nodeB] = false;
		this.edges[nodeB][nodeA] = false;
		this.size--;
	}
	
	private boolean checkEdge(int nodeA, int nodeB) {
		return this.edges[nodeA][nodeB] && this.edges[nodeB][nodeA];
	}
	
	@Override
	public List<Integer> neighbours(int vertex) {
		isVertex(vertex);
		boolean[] vecinos = this.edges[vertex];
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for( int i = 0; i < vecinos.length; i++ )
			if( vecinos[i] )
				ret.add( i );
		return ret;
	}
	
	private boolean isVertex(int vertex) {
		if( !checkVertex(vertex) )
			throw new IllegalArgumentException("Vertex does not exist. Vertex = " + vertex);
		return true;
	}
	
	private boolean checkVertex(int vertex) {
		return vertex >= 0 && vertex < this.order ? true : false;
	}

	@Override
	public void addVertex() {
		boolean[][] newEdges = new boolean[this.order+1][this.order+1];
		for( int i = 0; i < this.edges.length; i++ ) {
			for( int j = 0; j < this.edges[i].length; j++ ) {
				newEdges[i][j] = this.edges[i][j];
			}
		}
		this.edges = newEdges;
		this.order++;
	}
	
	@Override
	public void removeVertex(int vertex) {
		isVertex(vertex);
		removeEdges(vertex);
		boolean[][] newEdges = new boolean[this.order-1][this.order-1];
		int newEdgesIndexI;
		int newEdgesIndexJ;
		newEdgesIndexI = 0;
		for( int i = 0; i < this.edges.length; i++ ) {
			if( i != vertex ) {
				newEdgesIndexJ = 0;
				for( int j = 0; j < this.edges[i].length; j++ ) {
					if( j != vertex ) {
						newEdges[newEdgesIndexI][newEdgesIndexJ] = this.edges[i][j];
						newEdgesIndexJ++;
					}
				}
				newEdgesIndexI++;
			}
		}
		this.edges = newEdges;
		this.order--;
	}
	
	private void removeEdges(int vertex) {
		List<Integer> neighbours = neighbours(vertex);
		for( Integer i : neighbours ) {
			removeEdge(vertex, i);
		}
	}

}
