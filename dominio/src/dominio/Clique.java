package dominio;



import java.awt.*;
import java.util.*;
import java.util.List;
 
/**
 * @author pau
 */
public class Clique extends Algorithm{
    private float preWeight, currWeight;
    private boolean end;
    private int pMax, pSecMax, sMax, sSecMax;
 
    
    public Solution getSolution(Graph g) throws Exception {
        long before = System.nanoTime();
 
        pMax = pSecMax = sMax = sSecMax = -1;
        preWeight = currWeight = -1;
        end = false;
 
        Solution s;
        DJSets ds = new DJSets(0);
        HashMap<Edge, Integer> edgeIndexes = new HashMap<>();
 
        // We get all the edges
        List<Edge> edges = new ArrayList<Edge>(g.getAllEdges());
 
        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge edge, Edge t1) {
                return -Float.compare(edge.getWeight(), t1.getWeight());
            }
        });
 
 
        ArrayList<Pair<Edge, Pair<Node, Node>>> edgesInfo = new ArrayList<>();
        for (Edge e : edges) {
            Pair<Node, Node> p = (g.getNodesConnectedBy(e));
            edgesInfo.add(new Pair<Edge, Pair<Node, Node>>(e, g.getNodesConnectedBy(e)));
            g.removeEdge(e);
        }
        int k = 3;
        phase1(g, edgesInfo, k, ds, edgeIndexes);
        s = buildSolution(edgeIndexes, ds, g);
        long after = System.nanoTime();
        s.setTime(after - before);
        return s;
    }
 
    private void phase1(Graph<Node> g, ArrayList<Pair<Edge, Pair<Node, Node>>> edges, int k, DJSets ds, HashMap<Edge, Integer> edgeIndexes) throws Exception {
        k = 3;
        for (Pair<Edge, Pair<Node, Node>> p : edges) {
            preWeight = currWeight;
            currWeight = p.getFirst().getWeight();
            //int sizeMax = max.second();
            //int sizeSecMax = secMax.second();
            int sizeMax = sMax;
            int sizeSecMax = sSecMax;
            if (!end) end = (sizeSecMax != -1 && sizeMax != -1 && sizeMax >= 2*sizeSecMax && preWeight != -1 && preWeight != currWeight);
            /* System.out.println("preWeight: " + preWeight + ", currWeight: " + currWeight + ", sizeMax: " +
                                sizeMax + ", sizeSecMax: " + sizeSecMax + ", end: " + end);
                                */
 
            Edge e = p.getFirst();
            Pair<Node, Node> nodes = p.getSecond();
            if (e == null) throw new Exception("Phase 1 found a null edge.");
            else {
                Node v1 = nodes.getFirst();
                Node v2 = nodes.getSecond();
                g.addEdge(v1, v2, e);
                if (!end && g.getAdjacentNodesTo(v1).size() >= k - 1 && g.getAdjacentNodesTo(v2).size() >= k - 1) {
                    // It may release a clique. Let's find common neighbours of its nodes.
                    // Collect all neighbours of both v1 and v2
                    Set<Node> NV1 = g.getAdjacentNodesTo(v1);
                    Set<Node> NV2 = g.getAdjacentNodesTo(v2);
                    Set<Node> commonNeighbours = new HashSet<Node>();
                    for (Node n : NV1) {
                        if (NV2.contains(n)) {
                            // n is a common neighbour
                            commonNeighbours.add(n);
                        }
                    }
                    // "We found " + commonNeighbours.size() + " common neighbours.\n"
                    for (Node n : commonNeighbours) {
                        // We take advantage of k being 3.
                        phase2(g, new Node[] {n, v1, v2}, ds, edgeIndexes);
                    }
                }
            }
        }
    }
 
    private void phase2(Graph<Node> g, Node[] threeClique, DJSets ds, HashMap<Edge, Integer> edgeIndexes) throws Exception {
        int k = 3;
        if(threeClique.length != k) throw new IllegalArgumentException("Pahse 2 received a non 3-clique.");
        Edge[] edges = new Edge[k];
        edges[0] = g.getEdge(threeClique[0], threeClique[1]);
        edges[1] = g.getEdge(threeClique[0], threeClique[2]);
        edges[2] = g.getEdge(threeClique[1], threeClique[2]);
 
        if (!edgeIndexes.containsKey(edges[0])) { edgeIndexes.put(edges[0], edgeIndexes.size()); ds.add(); }
        if (!edgeIndexes.containsKey(edges[1])) { edgeIndexes.put(edges[1], edgeIndexes.size()); ds.add(); }
        if (!edgeIndexes.containsKey(edges[2])) { edgeIndexes.put(edges[2], edgeIndexes.size()); ds.add(); }
        if (ds.size() != edgeIndexes.size()) {
            //System.out.print("Mida ds: " + ds.size() + ". Mida edgeIndexes: " + edgeIndexes.size()+ "\n");
            throw new Exception("Inconsistency: DJSets of different size than the edgeIndexes.");
        }
 
        int newSize = -1;
 
        // Every (2)-clique (there are 3) belong to the same community, so we merge them
        int rootA = ds.find((edgeIndexes.get(edges[0])));
        int rootB = ds.find((edgeIndexes.get(edges[1])));
        if (rootA != rootB) newSize = ds.union(rootA, rootB);
 
        rootA = ds.find((edgeIndexes.get(edges[0])));
        rootB = ds.find(edgeIndexes.get(edges[2]));
        if (rootA != rootB) newSize = ds.union(rootA, rootB);
        int root = ds.find(edgeIndexes.get(edges[0]));
        //System.out.println("Mida nova comunitat:" + newSize);
 
        if (newSize > sMax) {
            // if it is a new community, we update the second largest community
            if (pMax == -1 || ds.find(root) != ds.find(pMax)) {
                //if (!max.first().equals(-1)) System.out.println("max is at " + ds.find(max.first()));
                if (pSecMax == -1 || ds.find(pSecMax) != ds.find(pMax)) {
                    sSecMax = sMax;
                    pSecMax = pMax;
                }
            }
            pMax = edgeIndexes.get(edges[0]);
            sMax = newSize;
        } else if (newSize > sSecMax) {
            if (pSecMax == -1 || ds.find(pSecMax) != ds.find(pMax)) {
                pSecMax = edgeIndexes.get(edges[0]);
                sSecMax = newSize;
            } else {
                pSecMax = sSecMax = -1;
            }
        }
        // If the 2 largest communitites have merged in one, we correct the SecMax
        if (pSecMax != -1 && pMax != -1 && ds.find(pSecMax) == ds.find(pMax)) {
            pSecMax = sSecMax = -1;
        }
 
        /* Uncomment for debugging purposes
        System.out.println("Max:  pos: " + pMax + ", size: "  + sMax);
        System.out.println("2nd:  pos: " + pSecMax + ", size: "  + sSecMax);
 
        for (Edge e : edgeIndexes.keySet()) {
            Node a = (Node) g.getNodesConnectedBy(e).first();
            Node b = (Node) g.getNodesConnectedBy(e).second();
 
            System.out.print("The edge " + a.getId() + " - " + b.getId() + " is in :" + ds.getParent(i) +"\n");
            System.out.print(b.getId() + "is in " + ds.find(i)+"\n");
        }
        */
    }
 
    private Solution buildSolution(HashMap<Edge, Integer> edgeIndexes, DJSets ds, Graph g) {
        Solution s = new Solution();
        HashMap<Integer, Community> comms = new HashMap<>();
        for (Edge e : edgeIndexes.keySet()) {
            Community c;
            int commNum = ds.find(edgeIndexes.get(e));
            if (comms.containsKey(commNum)) c = comms.get(commNum);
            else { c = new Community(); comms.put(commNum, c); }
            c.addNode((Node) g.getNodesConnectedBy(e).getFirst());
            c.addNode((Node) g.getNodesConnectedBy(e).getSecond());
        }
        for (Community c : comms.values()) {
            s.addCommunity(c);
        }
        return s;
    }
}
