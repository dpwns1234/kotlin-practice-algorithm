import java.util.*
import kotlin.math.max

// Silver 2
class Problem1874 {

    private var orderNum = 1
    private val myStack = Stack<Int>()
    private val answerStr = StringBuilder()

    // silver 2
    fun solve() {
        val size = readln().toInt()
        for (i in 0 until size) {
            val num = readln().toInt()
            isSuccess(size, num)
        }

        // 스택이 비어 있지 않으면 수열 만들기 실패
        if (myStack.isEmpty()) print(answerStr)
        else print("NO")
    }

    private fun isSuccess(size: Int, num: Int){
        // orderNum이 size(최대값)보다 크다는 것은 수열 반환에 실패한 것
        while (myStack.size <= size) {
            // 스택이 비어있으면 연속된 orderNum을 push한다.
            if (myStack.isEmpty()) {
                myStack.push(orderNum++)
                answerStr.append("+\n")
            }

            if (myStack.peek() == num) { // 같은게 나오면 pop
                myStack.pop()
                answerStr.append("-\n")
                break
            }
            else {                      // 반대로 다른게 나오면 push
                myStack.push(orderNum++)
                answerStr.append("+\n")
            }
        }
    }
}


// Silver 5(Greedy) - 40min
class Problem14916 {
    fun solve() {

        val money = readln().toInt()
        val coinNum5 = money / 5
        var coinNum2 = 0
        var answerCoinNum = 0

        // money가 짝수면 5를 짝수 배로
        if(money % 2 == 0) {
            if(coinNum5 % 2 == 0) {
                coinNum2 = (money % 5) / 2
                answerCoinNum = coinNum5 + coinNum2
            }
            // 5원 동전을 하나 빼고 계산한다.
            else {
                coinNum2 = (money % 5 + 5) / 2
                answerCoinNum = coinNum5-1 + coinNum2
            }
        }
        // money가 홀수면 5를 홀수 배로
        else {
            // 5원 동전을 하나 뺴고, 계산한다.
            if(coinNum5 % 2 == 0) {
                coinNum2 = (money % 5 + 5) / 2
                answerCoinNum = coinNum5-1 + coinNum2
            }
            else {
                coinNum2 = (money % 5) / 2
                answerCoinNum = coinNum5 + coinNum2
            }
        }

        if(money < 5 && money % 2 != 0)
            answerCoinNum = -1


        // 짝수 - 짝수 - 짝수 = 짝수 Or 짝수 - 홀수 - 짝수 = 홀수
        // 홀수 - 짝수 - 짝수 = 홀수 Or 홀수 - 홀수 - 짝수 = 짝수
        // 즉, money가 짝수면 5를 짝수로, money가 홀수면 5를 홀수로 만들어주면 됨.
        // 단, money가 5 미만이면 조절을 못함. 그 경우만 -1을 리턴

        print(answerCoinNum)
    }
}

// Silver 4(Greedy) - 28min
class Problem2217 {
    fun solve() {
        val n = readln().toInt()
        val lopeList = mutableListOf<Int>()
        for(i in 0 until n) {
            val lope = readln().toInt()
            lopeList.add(lope)
        }
        lopeList.sortDescending() // 내림차순 정렬

        var weight = 0
        var maxWeight = 0
        for(i in lopeList.indices) {
            weight = lopeList[i] * (i+1)
            if(maxWeight < weight)
                maxWeight = weight
        }

        print(maxWeight)
    }
}

// Silver 4(Greedy) - 40 min
class Problem1758 {
    fun solve() {
        val n = readln().toInt()
        val tipList = mutableListOf<Int>()
        for(i in 0 until n) {
            val tip = readln().toInt()
            tipList.add(tip)
        }
        tipList.sortDescending()    // 내림차순 정렬
        val sumOfTip = getSumOfTip(n, tipList)
        print(sumOfTip)
    }

    private fun getSumOfTip(n: Int, tipList: List<Int>): Long {
        var sumOfTip: Long = 0
        for(i in 0 until n) {
            var decidedTip = tipList[i] - (i+1 - 1)
            if(decidedTip < 0)
                decidedTip = 0

            sumOfTip += decidedTip
        }
        return sumOfTip
    }
}
