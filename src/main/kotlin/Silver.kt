import java.util.*
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt

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


// Silver 5(Greedy) - 40min (resolve 30min)
class Problem14916 {
    fun solve() {
        val money = readln().toInt()

        var coinNum = money / 5
        var restMoney = money % 5
        if(restMoney % 2 == 0)
            coinNum += (restMoney / 2)
        else {
            coinNum -= 1 // 5원을 덜 사용했기 떄문에
            restMoney += 5
            coinNum += (restMoney / 2)
        }

        if(money == 1 || money == 3)
            coinNum = -1

        print(coinNum)
    }
}

// Silver 4(Greedy) - 28min (resolve 15min)
class Problem2217 {
    fun solve() {
        val n = readln().toInt()
        val lopeList = mutableListOf<Int>()
        for(i in 0 until n) {
            val lope = readln().toInt()
            lopeList.add(lope)
        }
        lopeList.sortDescending() // 내림차순 정렬
        for(i in lopeList.indices) {
            lopeList[i] *= (i+1)
        }
        val maxWeight = lopeList.max()
        print(maxWeight)
    }
}

// Silver 4(Greedy) - 40 min (resolve 22min)
class Problem1758 {
    fun solve() {
        var answerTip: Long = 0
        val tipList = mutableListOf<Long>()
        val n = readln().toInt()
        for(i in 0 until n) {
            tipList.add(readln().toLong())
        }
        tipList.sortDescending()

        for(index in tipList.indices) {
            val actualTip = tipList[index] - index // 원래는 순서 - 1 이지만 index 가 0 부터 시작이므로 (order-1)로 바로 사용 가능.
            if(actualTip <= 0) // 내림차순으로 정렬했으므로 뒤의 팁들도 모두 0원처리가 돼서 의미없는 반복문을 종료한다.
                break

            answerTip += actualTip
        }
        print(answerTip)
    }
}

// Silver 3(Greedy) - 1 hour over.. (resolve 25min)
class Problem13305 {
    fun solve() {
        var costSum: Long = 0
        val n = readln().toInt()
        val distanceList = readln().split(" ")
        val oilCostList = readln().split(" ")

        var lowCost = oilCostList[0].toLong()
        for(i in distanceList.indices) {
            val oilCost = oilCostList[i].toLong()
            val distance = distanceList[i].toLong()
            if(oilCost < lowCost) {
                lowCost = oilCost
            }

            costSum += (lowCost * distance)
        }
        print(costSum)
    }
}

// Silver 3 - 25 min (Greedy)
class Problem20115 {
    fun solve() {
        val scr = Scanner(System.`in`)
        val n = scr.nextLong()
        val amountList = mutableListOf<Long>()
        for(i in 0 until n) amountList.add(scr.nextLong())

        // 제일 큰 용량을 제외한 값들의 합 / 2를 해준다. (결합법칙)
        val restAmount = (amountList.sum() - amountList.max()).toDouble() / 2
        // 제일 큰 용량 + 절반씩 잃은 것들의 합
        val answer = amountList.max() + restAmount
        print(answer)
    }
}

// Silver 2 - 35 min (Greedy) (resolve 20min)
class Problem1541 {
    fun solve() {
        var answerSum = 0
        val str = readln()
        val numList = str.split('+', '-')
        val signList = mutableListOf<Char>()
        for(ch in str) {
            if (ch == '+' || ch == '-')
                signList.add(ch)
        }

        answerSum = numList[0].toInt()
        for(i in signList.indices) {
            if(signList[i] == '+')
                answerSum += numList[i+1].toInt()
            else {
                answerSum -= minusSum(numList, i+1)
                break
            }
        }

        print(answerSum)
    }

    private fun minusSum(numList: List<String>, startIndex: Int): Int {
        var sum = 0
        for(i in startIndex until numList.size) {
            sum += numList[i].toInt()
        }
        return sum
    }
}

// Silver 2 - 50 min (Greedy) (resolve 20min)
class Problem16953 {
    fun solve() {
        var answer = 1
        val str = readln().split(" ")
        val a = str[0].toInt()
        var b = str[1].toInt()

        // a == b가 되면 변환 성공
        while(a != b) {
            // b가 a보다 작아지면 변환 실패
            if (b < a){
                answer = -1
                break
            }
            // 일의 자리가 1이냐
            else if(b%10 == 1)
                b /= 10
            // 짝수냐
            else if(b%2 == 0)
                b /= 2
            // 1이 아닌 홀수일 경우 변환 실패
            else {
                answer = -1
                break;
            }
            answer++
        }

        print(answer)
    }
}

// Silver 1 - 1hour 30min (Greedy)
class Problem1931 {
    fun solve() {
        var answerCount = 1
        val timeTable = input()
        timeTable.sortWith(compareBy ({ it.second }, {it.first})) // 끝나는 시간이 작은 순으로 정렬 + 시작 시간이 같으면 그것도 오름차순으로 정렬

        var previousMeeting = timeTable[0]
        // 전 회의의 끝나는 시간이 제일 마지막 회의시간의 끝나는 시간과 같아지면 종료
        for(index in 1 until timeTable.size) {
            val meetingStartTime = timeTable[index].first
            // 전 미팅의 끝나는 시간이 그 다음 미팅 시작시간이랑 겹치지 않으면 그 회의로 결정!
            if (previousMeeting.second > meetingStartTime) // 시간이 겹침
                continue
            else {
                previousMeeting = timeTable[index]
                answerCount++
            }
        }
        print(answerCount)
    }

    private fun input(): MutableList<Pair<Int, Int>> {
        val n = readln().toInt()
        val timeTable = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until n) {
            val str = readln().split(" ")
            val startTime = str[0].toInt()
            val endTime = str[1].toInt()

            val meeting = Pair(startTime, endTime)
            timeTable.add(meeting)
        }
        return timeTable
    }
}

// Silver2 - 2 hour (Greedy)
class Problem21314 {
    private val ZERO = "0"
    private val ONE = "1"
    fun solve() {
        val str = readln()
        val maxMK = getMaxMK(str)
        val minMK = getMinMK(str)
        println(maxMK + "\n" + minMK)

    }

    private fun getMaxMK(str:String): String {
        val MKNumber = StringBuilder("")
        var cnt = 0

        for(ch in str) {
            if(ch == 'M') {
                cnt++
            }
            else if(ch == 'K') {
                val temp = "5" + ZERO.repeat(cnt)
                MKNumber.append(temp)
                cnt = 0
            }
        }
        if(str.last() == 'M') {
            val temp = ONE.repeat(cnt)
            MKNumber.append(temp)
        }

        return MKNumber.toString()
    }
    private fun getMinMK(str: String): String {
        val MKNumber = StringBuilder("")
        var flag = false
        for(ch in str) {
            if(ch == 'M' && !flag) {
                MKNumber.append('1')
                flag = true
            }
            else if (ch == 'M' && flag) {
                MKNumber.append('0')
            }
            else if (ch == 'K') {
                MKNumber.append('5')
                flag = false
            }
        }

        return MKNumber.toString()
    }
}

// Silver5 - 40min (Binary Search)
class Problem1789 {
    fun solve() {
        val S = readln().toLong()
        var start: Long = 1
        var end: Long = S
        var mid: Long = 0
        while(end >= start) {
            mid = (start+end)/2
            val sigmaMid = mySum(mid)
            if(sigmaMid < S) start = mid+1
            else if(sigmaMid > S) end = mid-1
            else {
                print(mid)
                return
            }
        }
        print(end)
    }

    private fun mySum(n: Long): Long {
        var sum: Long = 0
        for(i in 1..n)
            sum += i

        return sum
    }
}

// Silver5 - 30min(Binary Search)
class Problem10815 {
    fun solve() {
        val n = readln().toInt()
        val str1 = readln().split(" ")
        val firstSet = mutableSetOf<Int>()
        for(i in 0 until n)
            firstSet.add(str1[i].toInt())

        val m = readln().toInt()
        val str2 = readln().split(" ")
        val secondList = mutableListOf<Int>()
        for(i in 0 until m)
            secondList.add(str2[i].toInt())

        for(element in secondList) {
            if(firstSet.contains(element)) print("1 ")
            else print("0 ")
        }
    }

    private fun binarySearch(list: List<Int>, target: Int): Boolean {
        var start = 0
        var end = list.lastIndex
        while(start <= end) {
            val mid = (start+end)/2
            if(target > list[mid]) start = mid+1
            else if (target < list[mid]) end = mid-1
            else  return true // target을 찾은 경우
        }
        return false
    }
}

// Silver3 - 40min (Binary Search유형이지만, 그냥 풀음)
class Problem2512 {
    fun solve() {
        // input
        val n = readln().toInt()
        val str = readln().split(" ")
        val requiredBudget = mutableListOf<Int>()
        for(budget in str) {
            requiredBudget.add(budget.toInt())
        }
        val totalBudget = readln().toInt()

        requiredBudget.sortDescending() // 큰 순서대로 정렬
        var maginotBudget = 0
        var repeatNum = 0
        var budgetSum = requiredBudget.sum()
        for(maxBudget in requiredBudget) {
            budgetSum += (maxBudget * repeatNum)
            if(totalBudget >= budgetSum) { // 총예산이 더 많다 -> 여기까지 원하는만큼 줄 수 있다.
                maginotBudget = maxBudget
                break
            }

            repeatNum++
            budgetSum -= (maxBudget*repeatNum) // -n번째 max
        }
        if(repeatNum == 0) {
            print(maginotBudget)
            return
        }

        val answerBudget = maginotBudget + (totalBudget - budgetSum) / repeatNum
        print(answerBudget)
    }
}

// Silver2 - 2 hour (Binary Search)
class Problem1654 {
    fun solve() {
        val str = readln().split(" ")
        val K = str[0].toInt()
        val N = str[1].toLong()
        val lenList = mutableListOf<Int>()
        for(i in 0 until K) {
            lenList.add(readln().toInt())
        }

        var start:Long = 0
        var end:Long = lenList.max().toLong() + 1 // mid가 0가 되는 상황을 막아줌.
        while(start < end) {
            val mid = (start+end) / 2
            val countSum = calculateCountSum(lenList, mid)
            if(countSum > N)
                start = mid+1
            else if(countSum < N)
                end = mid
            else {
                start = mid+1
            }
        }

        print(start-1)
    }

    private fun calculateCountSum(lenList: List<Int>, mid: Long): Long {
        var cnt:Long = 0
        for(len in lenList) {
            cnt += (len / mid)
        }
        return cnt
    }
}