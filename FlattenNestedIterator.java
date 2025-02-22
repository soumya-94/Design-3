//Time complexity : O(1) for both operations
//Space complexity : O(depth of list) stack space

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> stack;
    NestedInteger nextElement;

    public NestedIterator(List<NestedInteger> nestedList) {

        stack = new Stack();
        nextElement = null;
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() {

        while(!stack.isEmpty())
        {
            //iterator on top of stack has finished, pop it
            if(!stack.peek().hasNext())
                stack.pop();
            //check if its an integer
            else if((nextElement = stack.peek().next()).isInteger())
                return true;
            else //its a list
                stack.push(nextElement.getList().iterator());
        }

        //stack becomes empty
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
