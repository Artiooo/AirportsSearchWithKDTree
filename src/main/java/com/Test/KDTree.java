package com.Test;

import java.util.PriorityQueue;

public class KDTree {
    KDNode root;

    public static class KDNode {
        private Airport a;
        private KDNode left;
        private KDNode right;

        public KDNode(Airport a) {
            this.a = a;
            this.left = null;
            this.right = null;
        }

        public Airport getA() {
            return a;
        }
    }

    private KDNode addRecursive(KDNode current, Airport airport, int depth) {
        if (current == null)
            return new KDNode(airport);
        if (depth % 2 == 0) //Каждый раз будем менять ось для сравнивания х/у
        {
            if (airport.getLatitude() < current.a.getLatitude()) // Сначала сравниваем по х
            {
                current.left = addRecursive(current.left, airport, depth + 1); //Если меньше - сажаем на левый лист
            } else if (airport.getLatitude() > current.a.getLatitude()) //Больше - на правый
            {
                current.right = addRecursive(current.right, airport, depth + 1);
            } else {
                return current;
            }
        } else {
            if (airport.getLongitude() < current.a.getLongitude()) // Теперь по y
            {
                current.left = addRecursive(current.left, airport, depth + 1); //Если меньше - сажаем на левый лист
            } else if (airport.getLongitude() > current.a.getLongitude()) //Больше - на правый
            {
                current.right = addRecursive(current.right, airport, depth + 1);
            } else {
                return current;
            }
        }
        return current;

    }

    public void add(Airport airport) {
        root = addRecursive(root, airport, 0);
    }

    public void traverseInOrder(KDNode node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.println(node.a);
            traverseInOrder(node.right);
        }
    }

    public KDNode nearestNeighbor(Airport airport) {
        return nearestNeighbor(root, airport, 0);
    }

    private KDNode nearestNeighbor(KDNode root, Airport airport, int depth) {
        if (root == null) return null;
        KDNode nextBranch = null;
        KDNode otherBranch = null;
        if(depth%2 == 0)
        {
            if(airport.getLatitude() < root.a.getLatitude())
            {
                nextBranch = root.left;
                otherBranch = root.right;
            }else
            {
                nextBranch = root.right;
                otherBranch = root.left;
            }
        }else
        {
            if(airport.getLongitude() < root.a.getLongitude())
            {
                nextBranch = root.left;
                otherBranch = root.right;
            }else
            {
                nextBranch = root.right;
                otherBranch = root.left;
            }
        }
        KDNode temp = nearestNeighbor(nextBranch, airport, depth+1);
        KDNode best = closest(temp, root, airport);
        double radiusSquared = Airport.distSquared(airport, best.a);
        double dist = depth%2==0 ? airport.getLatitude() - root.a.getLatitude() : airport.getLongitude() - root.a.getLongitude();
        if(radiusSquared >= dist*dist)
        {
            temp = nearestNeighbor(otherBranch, airport, depth+1);
            best = closest(temp, best, airport);
        }
        return best;

    }
    KDNode closest(KDNode n0, KDNode n1, Airport airport) {
        if (n0 == null) return n1;

        if (n1 == null) return n0;

        double d1 = Airport.distSquared(n0.a, airport);
        double d2 = Airport.distSquared(n1.a, airport);

        if (d1 < d2)
            return n0;
        else
            return n1;
    }


}
