package io.github.meijun.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Shortest path
 * Created by meijun on 6/7/2014.
 */

public class ShortestPath {
    public static final int INF = Integer.MAX_VALUE / 2;

    public static void dijkstra(V s) {
        PriorityQueue<E> que = new PriorityQueue<>();
        s.min = 0;
        que.offer(new E(s, 0));
        while (!que.isEmpty()) {
            E crt = que.poll();
            if (crt.cost > crt.to.min) continue;
            for (E e : crt.to.es) {
                int dis = crt.cost + e.cost;
                if (dis < e.to.min) {
                    e.to.min = dis;
                    que.offer(new E(e.to, e.to.min));
                }
            }
        }
    }

    public static void bellmanFord(V s) {
        Queue<V> que = new LinkedList<>();
        s.min = 0;
        que.offer(s);
        while (!que.isEmpty()) {
            V crt = que.poll();
            crt.inQue = false;
            for (E e : crt.es) {
                int dis = crt.min + e.cost;
                if (dis < e.to.min) {
                    e.to.min = dis;
                    if (!e.to.inQue) {
                        e.to.inQue = true;
                        que.offer(e.to);
                    }
                }
            }
        }
    }

    public static class V {
        public ArrayList<E> es = new ArrayList<>();
        public int min = INF;
        public boolean inQue = false;

        public void add(V to, int cost) {
            es.add(new E(to, cost));
        }
    }

    public static class E implements Comparable<E> {
        public V to;
        public int cost;

        public E(V to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(E o) {
            return cost - o.cost;
        }
    }
}