package com.grafos.grafos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class DirectedGraphTest {

	/*
	 * Constructor
	 */
	
	@Test
	void whenSomeoneTriesToInstanceAGraphWithZeroVerticesThenAnExceptionIsRisen() {
		Throwable exception = assertThrows(IllegalArgumentException.class, 
											() -> {@SuppressWarnings("unused")
												   DirectedGraph graph = new DirectedGraph(0); } );
		assertEquals( "Vertices must be greater than 0. Vertices = 0", exception.getMessage() );
	}
	
	@Test
	void whenSomeoneTriesToInstanceAGraphWithNegativeVerticesThenAnExceptionIsRisen() {
		Throwable exception = assertThrows(IllegalArgumentException.class, 
											() -> {@SuppressWarnings("unused")
												   DirectedGraph graph = new DirectedGraph(-1); } );
		assertEquals( "Vertices must be greater than 0. Vertices = -1", exception.getMessage() );
	}
	
	/*
	 * Order
	 */
	
	@Test
	void whenAGraphIsCreatedWithFiveVerticesThenItsOrderShouldBeFive() {
		DirectedGraph graph = new DirectedGraph(5);
		assertEquals( 5, graph.order() );
	}
	
	@Test
	void whenSomeoneAddsAVertexToTheGraphWithFiveVerticesThenItsOrderShouldBeSix() {
		DirectedGraph graph = new DirectedGraph(5);
		int oldVertices = graph.order();
		graph.addVertex();
		assertEquals( oldVertices + 1, graph.order() );
	}
	
	@Test
	void giverAGraphWithFiveVerticesWhenSomeoneRemovesOneVertexThenItsOrderShouldBeFour() {
		DirectedGraph graph = new DirectedGraph(5);
		int oldVertices = graph.order();
		graph.removeVertex(2);
		assertEquals( oldVertices - 1, graph.order() );
	}
	
	/*
	 * edges
	 */
	
	@Test
	void whenAnEdgeIsAddedThenNodeAIsNeighbourWithNodeB() {
		DirectedGraph graph = new DirectedGraph(2);
		graph.addEdge(0, 1);
		assertTrue( graph.neighbours(0).contains(1) );
	}
	
	@Test
	void whenAnEdgeIsAddedThenNodeBIsNotNeighbourWithNodeA() {
		DirectedGraph graph = new DirectedGraph(2);
		graph.addEdge(0, 1);
		assertFalse( graph.neighbours(1).contains(0) );
	}
	
	@Test
	void whenGraphIsCreatedThenSizeShouldBeZero() {
		DirectedGraph graph = new DirectedGraph(5);
		assertEquals( 0, graph.size() );
	}
	
	@Test
	void whenOnlyOneEdgeIsAddedThenSizeShouldBeOne() {
		DirectedGraph graph = new DirectedGraph(3);
		graph.addEdge( 0, 1 );
		assertEquals( 1, graph.size() );
	}
	
	@Test
	void givenAGraphWithThreeEdgesWhenAnEdgeIsAddedThenSizeShouldBeFour() {
		DirectedGraph graph = new DirectedGraph(4);
		graph.addEdge( 0, 1 );
		graph.addEdge( 0, 2 );
		graph.addEdge( 0, 3 );
		int currentSize = graph.size();
		graph.addEdge( 2, 3 );
		assertEquals( currentSize + 1, graph.size() );
	}
	
	@Test
	void givenAGraphWithThreeEdgesWhenAnEdgeIsRemovedThenSizeShouldBeTwo() {
		DirectedGraph graph = new DirectedGraph(4);
		graph.addEdge( 0, 1 );
		graph.addEdge( 0, 2 );
		graph.addEdge( 0, 3 );
		int currentSize = graph.size();
		graph.removeEdge( 0, 2 );
		assertEquals( currentSize - 1, graph.size() );
	}
	
	@Test
	void givenAVertexWithThreeNeighboursWhenANeighbourIsRemovedThenTheVertexShouldStillHasTheOtherTwoNeibourghs() {
		DirectedGraph graph = new DirectedGraph(4);
		graph.addEdge( 0, 1 );
		graph.addEdge( 0, 2 );
		graph.addEdge( 0, 3 );
		
		graph.removeEdge( 0, 2 );
		
		List<Integer> expectedList = new ArrayList<Integer>();
		expectedList.add( 1 );
		expectedList.add( 3 );
		assertEquals( expectedList, graph.neighbours(0) );
	}
	
	/*
	 * Degree
	 */
	
	@Test
	void whenAGraphIsCreatedThenItsVerticesShouldHaveDegreeZero() {
		DirectedGraph graph = new DirectedGraph(4);
		assertEquals( 0, graph.degree(2) );
	}
	
	@Test
	void whenAVertexHasTwoNeighboursThenItsDegreeShouldBeTwo() {
		DirectedGraph graph = new DirectedGraph(3);
		graph.addEdge( 0, 1 );
		graph.addEdge( 0, 2 );
		assertEquals( 2, graph.degree(0) );
	}
	
	/*
	 * Exceptions
	 */
	
	@Test
	void whenSomeoneTriesToRemoveANonExistentVertexThenAnExceptionIsRisen() {
		DirectedGraph graph = new DirectedGraph(2);
		Throwable exception = assertThrows( IllegalArgumentException.class, 
												() -> {graph.removeVertex(2);} );
		assertEquals( "Vertex does not exist. Vertex = 2", exception.getMessage() );
	}
	
	@Test
	void whenSomeoneTriesToRemoveANegativeVertexThenAnExceptionIsRisen() {
		DirectedGraph graph = new DirectedGraph(2);
		Throwable exception = assertThrows( IllegalArgumentException.class, 
												() -> {graph.removeVertex(-1);} );
		assertEquals( "Vertex does not exist. Vertex = -1", exception.getMessage() );
	}
	
	@Test
	void whenSomeoneTriesToAddAnEdgeFromANonExistentVertexThenAnExceptionIsRisen() {
		DirectedGraph graph = new DirectedGraph(2);
		Throwable exception = assertThrows( IllegalArgumentException.class, 
													() -> {graph.addEdge(2, 0);} );
		assertEquals( "Vertex does not exist. Vertex = 2", exception.getMessage() );
	}
	
	@Test
	void whenSomeoneTriesToAddAnEdgeFromAnExistentVertexToANonExistentVertexThenAnExceptionIsRisen() {
		DirectedGraph graph = new DirectedGraph(2);
		Throwable exception = assertThrows( IllegalArgumentException.class, 
													() -> {graph.addEdge(1, -1);} );
		assertEquals( "Vertex does not exist. Vertex = -1", exception.getMessage() );
	}
	
	@Test
	void whenSomeoneTriesToRemoveAnEdgeFromANonExistentVertexThenAnExceptionIsRisen() {
		DirectedGraph graph = new DirectedGraph(2);
		Throwable exception = assertThrows( IllegalArgumentException.class, 
													() -> {graph.removeEdge(2, 0);} );
		assertEquals( "Vertex does not exist. Vertex = 2", exception.getMessage() );
	}
	
	@Test
	void whenSomeoneTriesToRemoveAnEdgeFromAnExistentVertexToANonExistentVertexThenAnExceptionIsRisen() {
		DirectedGraph graph = new DirectedGraph(2);
		Throwable exception = assertThrows( IllegalArgumentException.class, 
													() -> {graph.removeEdge(1, -1);} );
		assertEquals( "Vertex does not exist. Vertex = -1", exception.getMessage() );
	}
	
	@Test
	void whenSomeoneTriesToAddAnExistentEdgeThenAnExceptionIsRisen() {
		DirectedGraph graph = new DirectedGraph(2);
		graph.addEdge(0, 1);
		Throwable exception = assertThrows( IllegalArgumentException.class, 
												() -> {graph.addEdge(0, 1);} );
		assertEquals( "Vertex already exists. Vertex = (0,1)", exception.getMessage() );
	}
	
	@Test
	void whenSomeoneTriesToRemoveANonExistentEdgeThenAnExceptionIsRisen() {
		DirectedGraph graph = new DirectedGraph(2);
		Throwable exception = assertThrows( IllegalArgumentException.class, 
												() -> {graph.removeEdge(0, 1);} );
		assertEquals( "Vertex does not exist. Vertex = (0,1)", exception.getMessage() );
	}

}
