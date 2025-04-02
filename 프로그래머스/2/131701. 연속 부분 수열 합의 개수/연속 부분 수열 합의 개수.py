def solution(elements):
    answer = 0
    numberSet = set()
    
    elementLen = len(elements)
    elements = elements * 2
    
    for i in range(elementLen):
        for j in range(elementLen):
            numberSet.add(sum(elements[j:j+i+1]))
            
    answer = len(numberSet)
    return answer