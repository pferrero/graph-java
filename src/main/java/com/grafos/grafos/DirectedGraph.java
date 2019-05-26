package com.grafos.grafos;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraph implements Graph {
	
	private List< List<Integer> > neighbourhood;
	private int size;
	
	public DirectedGraph(int vertices) {
		if( vertices <= 0 )
			throw new IllegalArgumentException("Vertices must be greater than 0. Vertices = " + vertices);
		this.neighbourhood = new ArrayList<>( vertices );
		for( int i = 0; i < vertices; i ++ )
			this.neighbourhood.add( new ArrayList<Integer>() );
		this.size = 0;
	}

	@Override
	public int order() {
		return this.neighbourhood.size();
	}
	
	@Override
	public void addVertex() {
		this.neighbourhood.add( new ArrayList<Integer>() );
	}
	
	@Override
	public void removeVertex(int vertex) {
		checkVertex(vertex);
		this.neighbourhood.remove( vertex );
	}
	
	private void checkVertex(int vertex) {
		if( vertex < 0 || vertex >= order() )
			throw new IllegalArgumentException("Vertex does not exist. Vertex = " + vertex);
	}

	@Override
	public int size() {
		return this.size;
	}
	
	@Override
	public void addEdge(int from, int to) {
		checkVertex(from);
		checkVertex(to);
		if( this.neighbourhood.get(from).contains(to) )
			throw new IllegalArgumentException("Vertex already exists. Vertex = (" + from + "," + to + ")");
		this.neighbourhood.get( from ).add( to );
		this.size++;

	}

	@Override
	public void removeEdge(int from, int to) {
		checkVertex(from);
		checkVertex(to);
		if( !this.neighbourhood.get(from).contains(to) )
			throw new IllegalArgumentException("Vertex does not exist. Vertex = (" + from + "," + to + ")");
		Integer oldNeighbour = to;
		this.neighbourhood.get( from ).remove( oldNeighbour );
		this.size--;
	}

	@Override
	public int degree(int vertex) {
		return this.neighbourhood.get(vertex).size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> neighbours(int vertex) {
		ArrayList<Integer> list = (ArrayList<Integer>) this.neighbourhood.get( vertex );
		return (List<Integer>) list.clone();
	}

}
