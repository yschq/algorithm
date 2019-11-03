package com.rrdream.ai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC42_TrappingRainWater {

    public static int trap(int[] height) {
        int result = 0;
        if(height.length == 0){
            return result;
        }
        HeightNode rootNode = new HeightNode(height[0]);
        HeightNode n = rootNode;
        for(int i = 1; i < height.length; i++){
            if(n.height == height[i]){
                n.count++;
                continue;
            }
            HeightNode node = new HeightNode(height[i]);
            n.rightNode = node;
            node.leftNode = n;
            n = node;
        }

        n = rootNode;
        while (n.leftNode != null || n.rightNode != null){
            if(n.leftNode == null){
                if(n.rightNode.rightNode == null){
                    break;
                }
                if(n.height <= n.rightNode.height){
                    n.rightNode.leftNode = null;
                }
                n = n.rightNode;
                continue;
            }
            if(n.rightNode == null){
                if(n.height < n.leftNode.height){
                    n.leftNode.rightNode = null;
                    n = rootNode;
                }
                continue;
            }

            if(n.height <= n.leftNode.height && n.height <= n.rightNode.height){
                
                if(n.leftNode.height <= n.rightNode.height){
                    result += n.count * (n.leftNode.height - n.height);
                    n.leftNode.count += n.count;
                    n.leftNode.rightNode = n.rightNode;
                    n.rightNode.leftNode = n.leftNode;
                    n = n.leftNode;
                } else {
                    result += n.count * (n.rightNode.height - n.height);
                    n.rightNode.count += n.count;
                    n.leftNode.rightNode = n.rightNode;
                    n.rightNode.leftNode = n.leftNode;
                    n = n.rightNode;
                }
            } else {
                if(n.height > n.leftNode.height){
                    n.leftNode = null;
                    rootNode = n;
                }
                n = n.rightNode;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        int result = trap(height);
        System.out.println("result:" + result);
    }
}

class HeightNode {
    public int height = 0;
    public int count = 1;
    public HeightNode leftNode;
    public HeightNode rightNode;

    HeightNode(int height){
        this.height = height;
    }
}
