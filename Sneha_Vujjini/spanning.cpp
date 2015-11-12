/*
 * Sneha_kruskals.cpp
 * Creating Minimum Spanning Tree using Kruskal's algorithm
 * Kruskal's Algorithm inturn uses union-find to detect cycles
 * 
 */


#include <iostream>
#include <climits>
#include <cstring>
#include <fstream>
#include <stdlib.h>

class UGraph  
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

//myFun for quicksort 
int myFun(const void* a, const void* b)
{
    UGraph* a1 = (UGraph*)a;
    UGraph* b1 = (UGraph*)b;
    return a1->Weight > b1->Weight;
}

//Union - Find Algorithm 

class Subset
{
	public: 
	
    int parent;
    int rank;
};

// Find set of an element i

int find(Subset subsets[], int i)
{
    if (subsets[i].parent != i)
        subsets[i].parent = find(subsets, subsets[i].parent);
 
    return subsets[i].parent;
}

// union of two sets of x and y

void Union(Subset subsets[], int x, int y)
{
    int xroot = find(subsets, x);
    int yroot = find(subsets, y);
 
    // Attach smaller rank tree under root of high rank tree
    
    if (subsets[xroot].rank < subsets[yroot].rank)
        subsets[xroot].parent = yroot;
    else if (subsets[xroot].rank > subsets[yroot].rank)
        subsets[yroot].parent = xroot;
 
    // If ranks are same, then make one as root and increment
    // its rank by one
    else
    {
        subsets[yroot].parent = xroot;
        subsets[xroot].rank++;
    }
}

//Minimum Spanning Tree using Kruskals Algorithm 

void kruskals(int N_edges, int N_vertices, UGraph edges[], int src)
{
	UGraph result[N_vertices];
	int e=0;
	
	//Sort edges in increasing order of weighs
	qsort(edges,N_edges, sizeof(edges[0]), myFun);


	Subset subsets[N_vertices];
        
	for (int v = 0; v < N_vertices; ++v)
    {
        subsets[v].parent = v;
        subsets[v].rank = 0;
    }

	// Repeat till all edges covered. 
	// If edge doesn't cause a cycle,Add to result 

	for(int i=0;i<N_edges-1;i++)
    {
		
		UGraph next_edge = edges[i];
		
		int x = find(subsets, mapElement(next_edge.Source));
        int y = find(subsets, mapElement(next_edge.Dest));
        
        if (x != y)
        {
            result[e++] = next_edge;
            Union(subsets, x, y);
        }
	}
    
    int TotalWeight = 0;
    // Display Minimum Spanning Tree
    std::cout<<"\nEdges in Minimum Spanning Tree";
    std::cout<<"\n------------------------------------------\n";
    for (int i = 0; i < e; ++i)
    {
        std::cout<<result[i].Source<<"\t"<< result[i].Dest<<"\t"<<result[i].Weight<<"\n";
        TotalWeight = TotalWeight + result[i].Weight;
	}
	std::cout<<"\n Total Weight = "<< TotalWeight;
}



int main(int argc, char **argv)
{
	using namespace std;
	using std::ifstream;
	int no_of_vertices, no_of_edges;
	char isDirected;
	
	//Taking Input from File-- input.txt
	
	ifstream myfile ("input.txt"); 
	
	if (myfile.is_open()) //Read vertices, edges and 'directed' flag
	{
		myfile>>no_of_vertices>>no_of_edges>>isDirected;
	}
	
	UGraph edges[no_of_edges];
	
	if (myfile.is_open())	//Read all edges
	{
		for(int i=0;i<no_of_edges;i++)
		{
			myfile>>edges[i].Source>>edges[i].Dest>>edges[i].Weight;
		}
	
	myfile.close();
	}
	
	//Continues only if directed graph
	if(isDirected == 'u' ||isDirected == 'U') 
	{
		std::cout<<"\n"<<"The following is given input: "<<"\n";
		for(int i=0;i<no_of_edges;i++)
		{
			std::cout<<edges[i].Source<<"\t"<<edges[i].Dest<<"\t"<<edges[i].Weight;
			std::cout<<"\n";
		}
		//shortestPath(no_of_edges, no_of_vertices, edges, 0);
		kruskals(no_of_edges, no_of_vertices, edges, 0);
	}
	else
	{
		std::cout<<"This program is valid for undirected graph only";
	}
	
	return 0;
}


