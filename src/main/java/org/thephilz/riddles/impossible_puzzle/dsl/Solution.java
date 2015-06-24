package org.thephilz.riddles.impossible_puzzle.dsl;

import org.thephilz.riddles.impossible_puzzle.Result;

/**
 * Created by phil on 6/23/15.
 */
public class Solution {
    protected Result result;


    protected Solution(Result result) {
        this.result = result;
    }

    public Result whatAreXAndY() {
        return this.result;
    }
}
