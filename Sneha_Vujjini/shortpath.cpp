/*
 * Sneha_ShortestPath.cpp
 * Finding Shortest Path of Directed Acyclic Graph
 * using Bellman-Ford algorithm
 * 
 */


#include <iostream>
#include <climits>
#include <cstring>
#include <fstream>

class DAGraph
{
	public:
		char Source;
		char Dest;
		int Weight;
		
};

int mapElement(char n)				//Mapping Variables onto integer values, for computing purposes
{
	switch(n){
		case 'A': return 0;
		case 'B': return 1;
		case 'C': return 2;
		case 'D': return 3;
		case 'E': return 4;
	}
	return 0;
}
char mapElement(int n)				//Mapping Variables onto integer values, for computing purposes
{
	switch(n){
		case 0: return 'A';
		case 1: return 'B';
		case 2: return 'C';
		case 3: return 'D';
		case 4: return 'E';
	}
	return '\0';
}

//Display graph 
void displayOutput(int dist[], int n)   
{
    std::cout<<"\nVertex   Distance from Source";
    std::cout<<"\n---------------------------\n";
    for (int i = 0; i < n; ++i)
        std::cout<<mapElement(i)<<"\t\t"<<dist[i]<<"\n";
}

//Shortest path using Bellman-Ford Algorithm
void shortestPath(int N_edges, int N_vertices, DAGraph edges[], int src)  
{
	int dist[N_vertices];
	
	// Set All initial Distances to Maximum limit. 
	for(int i=0;i<N_vertices; i++)
	{
		dist[i]=INT_MAX;
	}
	
	dist[src]=0;
	
	// For each edge,if dist[u] + weight < dist[v]  of edge uv, Update dist[v]
	// dist[v] = dist[u] + weight of edge uv
	
	for (int i=1; i<= N_vertices-1; i++)
	{
		for ( int j=0; j< N_edges; j++)
		{
			int u = mapElement(edges[j].Source);
			int v = mapElement(edges[j].Dest);
			int weight = edges[j].Weight;
			if(dist[u] + weight < dist[v])
			dist[v] = dist[u] + weight;
		}
	}
	displayOutput(dist, N_vertices);
	return; 
}


int main(int argc, char **argv)
{
	using namespace std;
	using std::ifstream;
	int no_of_vertices, no_of_edges;
	char isDirected;
	
	//Taking Input from File-- inpt.txt
	
	ifstream myfile ("inpt.txt"); 
	
	if (myfile.is_open()) //Reading vertices, edges and 'directed' flag
	{
		myfile>>no_of_vertices>>no_of_edges>>isDirected;
	}
	
	DAGraph edges[no_of_edges];
	
	if (myfile.is_open())	//Reading all edges
	{
		for(int i=0;i<no_of_edges;i++)
		{
			myfile>>edges[i].Source>>edges[i].Dest>>edges[i].Weight;
		}
	
	myfile.close();
	}
	
	//Continues only if directed graph
	
	if(isDirected == 'd' ||isDirected == 'D') 
	{
		std::cout<<"\n"<<"The following is given input: "<<"\n";
		for(int i=0;i<no_of_edges;i++)
		{
			std::cout<<edges[i].Source<<"\t"<<edges[i].Dest<<"\t"<<edges[i].Weight;
			std::cout<<"\n";
		}
		shortestPath(no_of_edges, no_of_vertices, edges, 0);
	}
	else
	{
		std::cout<<"This program is valid only for Directed Ascyclic graph";
	}
	
	return 0;
}


