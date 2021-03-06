/*
 * Copyright (c) 2012, Bin Zhang
 All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
Neither the name of the Bin Zhang nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS 
FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, 
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 */
package org.diting.collections;

import java.util.*;

/**
 * Provides convenient static utility methods for collections
 * @author Bin Zhang
 *
 */

public class CollectionUtils {
	
	/**
	 * Searches the specified list for specified object by specified key selector and comparator. The list must be sorted into ascending order according to the specified key selector and comparator(as by the <code>Enumerable.orderby</code> method), prior to making this call. If it is not sorted, the results are undefined. If the list contains multiple elements their key equals to the specified key, there is no guarantee which one will be found.
	 * 
	 * @param source the list to be searched
	 * @param key the key to be searched for
	 * @param selector the selector for selecting key of element
	 * @param comparator the comparator by which the list is ordered.
	 * @return the index of the search key, if it is contained in the list; otherwise, (-(insertion point) - 1). The insertion point is defined as the point at which the key would be inserted into the list: the index of the first element greater than the key, or list.size() if all elements in the list are less than the specified key. Note that this guarantees that the return value will be >= 0 if and only if the key is found.
	 */
    public static <T, TKey> int binarySearchBy(List<T> source, TKey key, Selector<T, TKey> selector, Comparator<TKey> comparator)
    {
    
    	
    	
    	int low = 0;
    	int high = source.size() - 1;
    	while (low <= high)
    	{
    		int middle = low + ((high - low) >> 1);
    		T item = source.get(middle);
    		TKey value = selector.select(item);
    		int cmp = comparator.compare(value, key);
    		if (cmp == 0)
    		{
    			return middle;
    		}
    		if (cmp < 0)
    		{
    			low = middle + 1;
    		}
    		else
    		{
    			high = middle - 1;
    		}
    	}
    	return ~low;
    	
    }
    
    /**
	 * Searches the specified list for specified object by specified key selector. The list must be sorted into ascending order according to the specified key selector and comparator(as by the <code>Enumerable.orderby</code> method), prior to making this call. If it is not sorted, the results are undefined. If the list contains multiple elements their key equals to the specified key, there is no guarantee which one will be found.
	 * 
	 * @param source the list to be searched
	 * @param key the key to be searched for
	 * @param selector the selector for selecting key of element
	 * @return the index of the search key, if it is contained in the list; otherwise, (-(insertion point) - 1). The insertion point is defined as the point at which the key would be inserted into the list: the index of the first element greater than the key, or list.size() if all elements in the list are less than the specified key. Note that this guarantees that the return value will be >= 0 if and only if the key is found.
	 */
    public static <T, TKey> int binarySearchBy(List<T> source, TKey key, Selector<T, TKey> selector)
    {
    	return binarySearchBy(source, key, selector, new NaturalComparator<TKey>());
    }
}
