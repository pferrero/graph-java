package com.grafos.grafos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class UndirectedGraphTest {

	/*
	 * constructor
	 */
	
	@Test
	void whenSomeoneTriesToInstanceAGraphWithZeroVerticesThenAnExceptionIsRisen() {
		Throwable exception = assertThrows( IllegalArgumentException.class, 
								() -> { @SuppressWarnings("unused")
										Graph graph = new UndirectedGraph(0); } );
		assertEquals( "Vertices must be greater than 0. Vertices = 0", exception.getMessage() );
	}
	
	@Test
	void whenSomeoneTriesToInstanceAGraphWithNegativeVerticesThenAnExceptionIsRisen() {
		Throwable exception = assertThrows( IllegalArgumentException.class, 
								() -> { @SuppressWarnings("unused")
										Graph graph = new UndirectedGraph(-1); } );
		assertEquals( "Vertices must be greater than 0. Vertices = -1", exception.getMessage() );
	}
	
	/*
	 * order
	 */
	
	@Test
	void whenAGraphIsCreatedWithFiveVerticesThenItsOrderShouldBeFive() {
		UndirectedGraph graph = new UndirectedGraph(5);
		assertEquals( 5, graph.order() );
	}
	
	/*
	 * Test addEdge
	 */
	
	@Test
	void addEdgeWithNonExistentNodeB() {
		UndirectedGraph graph = new UndirectedGraph(3);
		Throwable exception = assertThrows( IllegalArgumentException.class, 
								() -> {graph.addEdge(2, 3); } );
		assertEquals( "Vertex does not exist. Vertex = 3", exception.getMessage() );
	}
	
	@Test
	void addEdgeWithNonExistentNodeA() {
		UndirectedGraph graph = new UndirectedGraph(3);
		Throwable exception = assertThrows( IllegalArgumentException.class, 
								() -> {graph.addEdge(4, 0); } );
		assertEquals( "Vertex does not exist. Vertex = 4", exception.getMessage() );
	}
	
	@Test
	void whenAnEdgeIsAddedThenNodeAIsNeighbourWithNodeB() {
		UndirectedGraph graph = new UndirectedGraph(2);
		graph.addEdge(0, 1);
		assertTrue( graph.neighbours(0).contains(1) );
	}
	
	@Test
	void whenAnEdgeIsAddedThenNodeBIsNeighbourWithNodeA() {
		UndirectedGraph graph = new UndirectedGraph(2);
		graph.addEdge(0, 1);
		assertTrue( graph.neighbours(1).contains(0) );
	}
	
	/*
	 * Test size
	 */
	
	@Test
	void whenAGraphIsCreatedItsSizeShouldBeZero() {
		UndirectedGraph graph = new UndirectedGraph(5);
		assertEquals( 0, graph.size() );
	}
	
	@Test
	void givenAGraphWithNoEdgeswhenAnEdgeIsAddedThenThenGraphSizeIsOne() {
		UndirectedGraph graph = new UndirectedGraph(3);
		graph.addEdge(1, 2);
		assertEquals( 1, graph.size() );
	}
	
	/*
	 * Test degree
	 */
	
	@Test
	void whenSomeoneCallsADegreeOfANegativeVertexThenAnExceptionIsThrown() {
		UndirectedGraph graph = new UndirectedGraph(2);
		Throwable exception = assertThrows(IllegalArgumentException.class, 
											() -> {graph.degree(-1);} );
		assertEquals( "Vertex does not exist. Vertex = -1", exception.getMessage() );
	}
	
	@Test
	void givenAGraphWithNoEdgesThenItsVerticesDegreesAreZero() {
		UndirectedGraph graph = new UndirectedGraph(5);
		assertEquals( 0, graph.degree(4) );
	}
	
	@Test
	void givenAGraphWithNoEdgesWhenTwoEdgesAreAddedToOneVertexThenItsDegreeIsTwo() {
		UndirectedGraph graph = new UndirectedGraph(3);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		assertEquals( 2, graph.degree(0) );
	}
	
	/*
	 * Test neighbours
	 */
	
	@Test
	void whenSomeoneAsksForANonExistentVertexNeighboursThenAnExceptionIsThrown() {
		UndirectedGraph graph = new UndirectedGraph(3);
		Throwable exception = assertThrows( IllegalArgumentException.class, 
									() -> {graph.neighbours(3); } );
		assertEquals( "Vertex does not exist. Vertex = 3", exception.getMessage() );
	}
	
	@Test
	void givenAGraphWithNoEdgesThenTheNeighboursOfAVertexIsEmpty() {
		UndirectedGraph graph = new UndirectedGraph(3);
		assertEquals( new ArrayList<Integer>(), graph.neighbours(0) );
	}
	
	@Test
	void givenAVertexWithThreeNeighboursThenItsNeighboursListIsCorrect() {
		UndirectedGraph graph = new UndirectedGraph(5);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(0, 3);
		ArrayList<Integer> lista = new ArrayList<Integer>();
		lista.add(1);
		lista.add(2);
		lista.add(3);
		assertEquals( lista, graph.neighbours(0) );
	}
	
	/*
	 * Test addVertex
	 */
	
	@Test
	void givenAGraphWithFiveVerticesWhenAVertexIsAddedThenItsOrderIsSix() {
		UndirectedGraph graph = new UndirectedGraph(5);
		int ordenViejo = graph.order();
		graph.addVertex();
		assertEquals( ordenViejo + 1, graph.order() );
	}
	
	@Test
	void givenAGraphWithOneEdgeWhenAVertexIsAddedThenItsSizeIsTheSame() {
		UndirectedGraph graph = new UndirectedGraph(5);
		graph.addEdge(0, 4);
		int oldSize = graph.size();
		graph.addVertex();
		assertEquals( oldSize, graph.size() );
	}
	
	@Test
	void givenAGraphWhenAVertexIsAddedThenTheNeighboursOfTheNewVertexIsEmpty() {
		UndirectedGraph graph = new UndirectedGraph(5);
		graph.addVertex();
		assertEquals( new ArrayList<Integer>(), graph.neighbours(5) );
	}
	
	/*
	 * Test removeEdge
	 */
	
	@Test
	void whenAnEdgeIsRemovedThenNodeAIsNotNeighbourWithNodeB() {
		UndirectedGraph graph = new UndirectedGraph(2);
		graph.addEdge(0, 1);
		
		graph.removeEdge(1, 0);
		
		assertFalse( graph.neighbours(0).contains(1) );
	}
	
	@Test
	void whenAnEdgeIsRemovedThenNodeBIsNotNeighbourWithNodeA() {
		UndirectedGraph graph = new UndirectedGraph(2);
		graph.addEdge(0, 1);
		
		graph.removeEdge(1, 0);
		
		assertFalse( graph.neighbours(1).contains(0) );
	}
	
	@Test
	void whenSomeoneTriesToRemoveANonExistentEdgeThenAnExceptionIsThrown() {
		UndirectedGraph graph = new UndirectedGraph(2);
		Throwable exception = assertThrows(IllegalArgumentException.class, 
											() -> { graph.removeEdge(0, 1); });
		assertEquals( "This edge does not exist. Edge = (0,1)", exception.getMessage() );
	}
	
	/*
	 * Test removeVertex
	 */
	
	@Test
	void givenAGraphWithTwoVerticesWhenAVertexIsRemovedThenItsOrderIsOne() {
		UndirectedGraph graph = new UndirectedGraph(2);
		graph.removeVertex(0);
		assertEquals( 1, graph.order() );
	}
	
	@Test
	void givenAGraphWithTwoVerticesAndOneEdgeWhenAVertexIsRemovedThenItsSizeIsZero() {
		UndirectedGraph graph = new UndirectedGraph(2);
		graph.addEdge(1, 0);
		int oldSize = graph.size();
		
		graph.removeVertex(1);
		
		assertEquals( oldSize - 1, graph.size() );
	}
}
