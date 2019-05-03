
import java.util.*;

public class GraphImplementation implements Graph{
    public int vertices;
    public int[][] adjMatrix;

    public GraphImplementation(int vertices){
        this.vertices = vertices;
        adjMatrix = new int[vertices][vertices];
    }
    @Override
    public void addEdge(int src, int tar){
        adjMatrix[src][tar]=1;
    }
   
    @Override
    public List<Integer> neighbors(int vertex){
        List<Integer> neigh = new ArrayList<>();
	for(int i=0;i<adjMatrix.length;i++){
            if(adjMatrix[vertex][i]>0){
                    neigh.add(i);
            }
	}
        return neigh;

    }   
    /*
    public List<Integer> topologicalSortH(){
        List<Integer> incident = new ArrayList<>(vertices);
        List<Boolean> visited = new ArrayList<>(vertices);
        List<Integer> arr = new ArrayList<>(vertices);
        Queue<Integer> schedule = new LinkedList<>();
        for(int i=0;i<vertices;i++){ 
            incident.add(i, 0);
            visited.add(i,false);
        }
        for(int i=0;i<vertices;i++){
            for(int j=0;j<vertices;j++){
                if(adjMatrix[i][j]!=0){
                    int value = incident.get(j);
                    value += 1;
                    incident.set(j, value);
                }
            }
        }
        for(int i=0;i<vertices;i++){
            if(incident.get(i)==0){
                schedule.add(i);
                boolean value = visited.get(i);
                value = true;
                visited.set(i, value);
            }
        }
        while(schedule.isEmpty()==false){
            int vertex = schedule.peek();
            schedule.remove();
            arr.add(vertex);
            for(int i=0;i<vertices;i++){
                if(adjMatrix[vertex][i]!=0 && visited.get(i)==false){
                    int value = incident.get(i);
                    value -= 1;
                    incident.set(i, value);
                    if(incident.get(i)==0){
                        schedule.add(i);
                        visited.set(i, true);
                    }
                }
            }           
        }
        return arr;
    }
*/
    
    public List<Integer> topologicalSort(){
        int[] incident = new int[vertices];
        List<Integer> schedule = new LinkedList<>();
        for(int i=0;i<vertices;i++){
            for(int j=0;j<vertices;j++){
                if(adjMatrix[i][j]!=0){
                    incident[j]++;
                }
            }
        }
        for (int k=0;k<vertices;k++){
            for(int i=0;i<vertices;i++){
                if(incident[i]==0){
                    schedule.add(i);
                    incident[i] = -1;
                    List<Integer> neigh = neighbors(i);
                    for (int n : neigh) {
                        incident[n]--;
                    }
                }
            }
        }
        return schedule;
    }
}
