import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.properties.Delegates

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

    fun solve2() {
        val str = readln().split(" ")
        val a = str[0].toInt()
        val b = str[1].toInt()

        var value = b
        var cnt = 1
        while(value > a) {
            if(value % 10 == 1) {
                value /= 10
            }
            else if(value % 2 == 0) {
                value /= 2
            }
            else {
                print(-1)
                return
            }
            cnt++
        }

        if(value == a) print(cnt)
        else print(-1)
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

    fun solve2() {
        val str = readln().split(" ")
        val k = str[0].toInt()
        val n = str[1].toInt()
        val lengths = mutableListOf<Int>()
        repeat(k) {
            lengths.add(readln().toInt())
        }
        lengths.sort()
        val maxLength = upperBoundBS(lengths, n)
        print(maxLength)
    }

    private fun upperBoundBS(lenList: List<Int>, n: Int): Long {
        var st = 1L
        var en: Long = lenList.last().toLong() + 1
        while(st < en) {
            val mid = (st + en) / 2L
            val cnt = calculateCountSum(lenList, mid)
            if(cnt < n) {
                en = mid
            }
            else {
                st = mid + 1
            }
        }
        return st-1
    }

    private fun calculateCountSum(lenList: List<Int>, mid: Long): Long {
        var cnt:Long = 0
        for(len in lenList) {
            cnt += (len / mid)
        }
        return cnt
    }
}

// Silver4 - 10min (Binary Search)
class Problem1920 {
    fun solve() {
        val n = readln().toInt()
        val str1 = readln().split(" ")
        val nList = mutableListOf<Int>()
        for(e in str1) {
            nList.add(e.toInt())
        }
        val m = readln().toInt()
        val mList = readln().split(" ")

        nList.sort()
        for(e in mList) {
            if(isExist(nList, e.toInt())) println("1")
            else println("0")
        }
    }

    private fun isExist(nList: List<Int>, element: Int): Boolean {
        var st = 0
        var en = nList.lastIndex
        while(st <= en) {
            val mid = (st+en) / 2
            if(nList[mid] > element) {
                en = mid - 1
            }
            else if(nList[mid] < element) {
                st = mid + 1
            }
            else {
                return true
            }
        }
        return false
    }
}

// https://st-lab.tistory.com/267 ( 위의 문제 시리즈 문제, 링크 다 풀고 한 번 참고 )
// Silver2 - 3 hour (Binary Search)
class Problem10816 {
    fun solve() {
        // input
        val n = readln().toInt()
        val str1 = readln().split(" ")
        val nList = mutableListOf<Int>()
        for(e in str1) {
            nList.add(e.toInt())
        }

        nList.sort()
        val scr = Scanner(System.`in`)
        val M: Int = scr.nextInt()

        val sb = StringBuilder()

        for (i in 0 until M) {
            val key: Int = scr.nextInt()
            sb.append(upperBoundBS(nList, key) - lowerBoundBS(nList, key)).append(' ')
        }
        println(sb)
    }

    private fun lowerBoundBS(nList: List<Int>, element: Int): Int {
        var st = 0
        var en = nList.size
        while(st < en) {
            val mid =  st + ((en - st) / 2)
            if(nList[mid] < element) {
                st = mid + 1
            }
            else if(nList[mid] >= element) {
                en = mid
            }
        }
        return st
    }

    private fun upperBoundBS(nList: List<Int>, element: Int): Int {
        var st = 0
        var en = nList.size
        while(st < en) {
            val mid =  st + ((en - st) / 2)
            if(nList[mid] <= element) {
                st = mid + 1
            }
            else if(nList[mid] > element) {
                en = mid
            }
        }
        return st
    }
}

class Problem22871 {
    fun solve() {
        // input
        val n = readln().toInt()
        val str = readln().split(" ")
        val aList = mutableListOf<Pair<Int, Int>>() // Pair(index, a)
        for(i in str.indices) {
            val a = str[i].toInt()
            aList.add(Pair(i, a))
        }

        var startIndex = 0
        var pairMinK: Pair<Int, Long>
        var maxK = 0L
        while(startIndex < aList.lastIndex) {
            pairMinK = getMinKIndex(aList, startIndex)
            if(pairMinK.second >= maxK)
                maxK = pairMinK.second

            startIndex = pairMinK.first
        }
        println(maxK)
    }

    private fun getMinKIndex(aList: List<Pair<Int, Int>>, startIndex: Int): Pair<Int, Long> {
        var minK = 10000000L
        var minIndex = startIndex
        for(i in startIndex+1 until aList.size) {
            val k: Long = 1L * (aList[i].first - startIndex) * (1 + abs(aList[i].second - aList[startIndex].second))
            if(k < minK) {
                minK = k
                minIndex = i
            }
        }
        return Pair(minIndex, minK)
    }
}

// Silver3 - 30min (Dynamic Programming) 해답 봄
class Problem1463 {
    fun solve() {
        val x = readln().toInt()
        val dpList = mutableListOf<Int>()
        dpList.add(0)
        dpList.add(0)
        for(i in 2..x) {
            dpList.add(dpList[i-1] + 1)
            if(i % 2 == 0)
                dpList[i] = min(dpList[i], dpList[i/2] + 1)
            if(i % 3 == 0)
                dpList[i] = min(dpList[i], dpList[i/3] + 1)
        }
        println(dpList[x])
    }
}

class Problem2579 {
    fun solve() {
        val n = readln().toInt()
        val stairs = mutableListOf<Int>()
        for(i in 0 until n) {
            stairs.add(readln().toInt())
        }

        var scoreSum = 0
        var jumpStair = false
        for(i in 0 until stairs.size - 3) { // -3의 이유는 i+2 때문에 -2, 마지막 계단은 무조건 밟아야하므로 제외햐서 -1 총 -3
            if(jumpStair) {
                jumpStair = false
                continue
            }
            val sum1 = stairs[i] + stairs[i+1]
            val sum2 = stairs[i] + stairs[i+2]
            val max = max(sum1, sum2)
            val sum3 = stairs[i+1] + stairs[i+2]

            scoreSum += stairs[i] // 해당 계단 점수 + 해주기
            if(max < sum3) // 건너뛰기
                jumpStair = true
        }

        if(jumpStair) {
            scoreSum += stairs[stairs.lastIndex-1]
        }
        else {
            scoreSum += max(stairs[stairs.lastIndex], stairs[stairs.lastIndex-1])
        }
        println(scoreSum + stairs.last())
    }
}

// Silver5
class Problem1010 {
    fun solve() {
        val nRepeat = readln().toInt()
        for(i in 0 until nRepeat) {
            val str = readln().split(" ")
            val n = str[0].toLong()
            val m = str[1].toLong()
            println(combination(m, n))
        }
    }

    private fun combination(n: Long, r: Long): Long {
        var ret = 1L
        repeat(r.toInt()) { r ->
            ret *= (n-r)
            ret /= r+1
        }
        return ret
    }
}

// Silver1 - 40min (BFS)
class Problem1926 {
    private lateinit var vis: Array<Array<Int>>
    private lateinit var paper: Array<Array<Int>>
    private var row by Delegates.notNull<Int>()
    private var col by Delegates.notNull<Int>()
    fun solve() {

        // input
        val scr = Scanner(System.`in`)
        row = scr.nextInt()
        col = scr.nextInt()

        paper = Array(row) { Array(col) {0} }
        vis = Array(row) { Array(col) {0} }

        for(i in 0 until row) {
            for(j in 0 until col) {
                paper[i][j] = scr.nextInt()
            }
        }

        val drawingData = mutableListOf<Int>()
        for(i in 0 until row) {
            for(j in 0 until col) {
                if(vis[i][j] == 1) continue // 방문한 건 패스
                if(paper[i][j] == 0) continue // 그림이 그려지지 않은 곳이라면 패스
                val drawingArea = getAreaUsingBfs(i, j)
                drawingData.add(drawingArea)
            }
        }

        if(drawingData.isEmpty()) {
            println(0)
            println(0)
        }
        else {
            println(drawingData.size)
            println(drawingData.max())
        }
    }

    private fun getAreaUsingBfs(currentRow: Int, currentCol: Int): Int {
        val dx = arrayOf(1, -1, 0, 0)
        val dy = arrayOf(0, 0, -1, 1)

        val queue = LinkedList<Pair<Int, Int>>()
        var cnt = 1
        vis[currentRow][currentCol] = 1
        queue.add(Pair(currentRow, currentCol))

        while(!queue.isEmpty()) {
            val current = queue.pop()
            for(i in 0 until 4) {
                val nx = current.first + dx[i]
                val ny = current.second + dy[i]
                if(nx < 0 || nx >= row || ny < 0 || ny >= col) continue // paper의 범위를 넘지 않도록 조절
                if(vis[nx][ny] == 1 || paper[nx][ny] == 0) continue     // 방문했거나 그려지지 않은(0) 경우 넘어가기

                vis[nx][ny] = 1
                queue.push(Pair(nx, ny))
                cnt++ // 그림의 넓이 측정
            }
        }
        return cnt
    }
}


class Problem2178 {

    private lateinit var miro: Array<Array<Int>>
    private lateinit var vis: Array<Array<Int>>

    fun solve() {
        // input
        val str = readln().split(" ")
        val row = str[0].toInt()
        val col = str[1].toInt()

        miro = Array(row) { Array(col) {0} }
        vis = Array(row) { Array(col) {0} }

        for(i in 0 until row) {
            val data = readln()
            for(j in 0 until col) {
                miro[i][j] = data[j].digitToInt()
            }
        }

        bfs(row, col)
        println(vis[row-1][col-1])
    }

    private fun bfs(row: Int, col: Int) {
        val queue = LinkedList<Pair<Int, Int>>()
        val dx = listOf(1, 0, 0, -1)
        val dy = listOf(0, 1, -1, 0) // 동 남 북 서

        vis[0][0] = 1
        queue.add(Pair(0, 0))

        while(!queue.isEmpty()) {
            val current = queue.pop()
            for(i in 0 until 4) {
                val nx = dx[i] + current.first
                val ny = dy[i] + current.second

                if(nx == row-1 && ny == col-1) { // 도착지점인데, 방문한 이력이 있을 경우
                    if(vis[nx][ny] >= 1)
                        vis[nx][ny] = min(vis[nx][ny], vis[current.first][current.second]+1) // 도착지점 값을 최소값으로 변경
                    else {
                        vis[nx][ny] = vis[current.first][current.second] + 1 // 지금까지 이동해온 거리 + 1
                    }
                }
                if(nx >= row || nx < 0 || ny >= col || ny < 0) continue
                if(miro[nx][ny] == 0 || vis[nx][ny] >= 1) continue
                vis[nx][ny] = vis[current.first][current.second] + 1 // 지금까지 이동해온 거리 + 1
                queue.push(Pair(nx, ny))
            }
        }
    }
}

// Silver2 - 25min (BS)
class Problem2805 {
    fun solve() {
        val str = readln().split(" ")
        val n = str[0].toInt()
        val m = str[1].toInt()
        val scr = Scanner(System.`in`)
        val woodLengths = mutableListOf<Int>()
        repeat(n) {
            woodLengths.add(scr.nextInt())
        }
        woodLengths.sort()
        val maxLength = upperBoundBS(woodLengths, m)
        print(maxLength)

    }
    private fun upperBoundBS(woodLengths: List<Int>, m: Int): Int {
        var st = 0
        var en = woodLengths.last() + 1
        while(st < en) {
            val mid = (st + en) / 2
            val extraLength = getExtraWoodLength(woodLengths, mid)
            if(extraLength >= m) { // length 늘려야
                st = mid + 1
            }
            else {
                en = mid
            }
        }

        return st - 1 // upper이기에 -1
    }

    private fun getExtraWoodLength(woodLengths: List<Int>, mid: Int): Long {
        var sum = 0L
        for(wood in woodLengths) {
            if(wood - mid < 0) continue // 음수는 계산 x
            sum += (wood - mid)
        }
        return sum
    }
}

class Problem21608 {
    fun solve() {
        val scr = Scanner(System.`in`)
        val likes = HashMap<Int, List<Int>>()
        val n = readln().toInt()
        for(row in 0 until n*n) {
            var student = 0
            val students = mutableListOf<Int>()
            for(i in 0 until 5) {
                if(i == 0) student = scr.nextInt()
                students.add(scr.nextInt())
            }
            likes[student] = students
        }


    }

    private fun first(n: Int): Pair<Int, Int> {
        val position = Array(n*n) { Array(n*n) { 0 } }
        val candidateCoords = mutableListOf<Pair<Int, Int>>()
        val dx = arrayOf(0, 0, 1, -1) // 동서남북
        val dy = arrayOf(1, -1, 0, 0)

        for(row in 0 until n*n) {
            for(col in 0 until n*n) {


                for(i in 0 until 4) {
                    val nx = dx[i] + row
                    val ny = dy[i] + col
                    if(nx >= n*n || ny >= n*n || nx < 0 || ny < 0) continue
                    if(position[nx][ny] == 0) continue
                }
            }
        }
        return Pair<Int, Int>(1, 1)
    }

    private fun second() {

    }

    private fun third() {

    }
}

// Silver4 - 40min (implementation)
class Problem10994 {
    private lateinit var drawing: Array<CharArray>
    fun solve() {
        // 정사각형을 그려준다. 1, 5, 9, 13, y = n+4 의 사각형을 그려준다. (y=입력값, n은 전의 값.)
        // 하나의 도화지를 만든다 생각. 그래서 큰 순서대로 차례로 그려나간다. 그리고 저장 후 다음 사각형을 그려나간다.
        // 입력값에 따라 몇x몇 사각형인지 체크하고, 그에 맞는 2차원 배열의 저장공간을 만든다.
        val n = readln().toInt()
        val row = 4*(n-1) + 1
        var order = 0   // 큰 사각형부터 그릴 것이기에, 그릴 순서를 정하기 위한 변수
        drawing = Array(row) { CharArray(row) {' '}}
        for(i in n downTo 1) {
            paintRect(i, order)
            order += 2
        }

        for(i in 0 until row) {
            for(j in 0 until row) {
                print(drawing[i][j])
            }
            println()
        }
    }

    private fun paintRect(n: Int, order: Int) {
        val row = 4*(n-1) + 1
        for (i in 0 until row) {
            // 세로 변 그려주기
            drawing[i + order][0 + order] = '*'          // order 변수를 통해 마치 사각형을 그릴 원점을 지정해주는 역할을 한다.
            drawing[i + order][row - 1 + order] = '*'

            // 위, 아래 가로 변 그려주기
            drawing[0 + order][i + order] = '*'
            drawing[row - 1 + order][i + order] = '*'
        }
    }
}

// Silver5 - 1 hour (Implementation)
class Problem20546 {
    fun solve() {
        // 입력
        val budge = readln().toInt()
        val stocks = mutableListOf<Int>()
        val str = readlnOrNull()?.split(" ")
        for(stock in str!!) {
            stocks.add(stock.toInt())
        }

        // bnp (살 수 있을 때 전부 사서 마지막 날에 팔기
        val bnp = methodBNP(budge, stocks)
        // timing (cnt변수 사용해서 상향, 하향 체크해서 전매도, 전매수하기)
        // 여러번 매도 매수 할 수 있는 것 체크, 한 번도 못 살 수도 있는 거 체크
        // 마지막날에는 떨어져도 사야함.
        val timing = methodTIMING(budge, stocks)
        if(bnp > timing)
            println("BNP")
        else if (bnp < timing)
            println("TIMING")
        else
            println("SAMESAME")
    }

    private fun methodBNP(budge: Int, stocks: List<Int>): Int {
        var count = 0   // 못 사면 0개 샀다는 걸 알려줌. (주식가격이 다 예산보다 비싼 경우)
        var cash = budge
        for(stock in stocks) {
            if(cash >= stock) {    // 예산보다 주식이 더 싸면
                count += (cash / stock)
                cash -= (count * stock)
            }
        }

        return cash + (count*stocks[13])
    }

    private fun methodTIMING(budge: Int, stocks: List<Int>): Int {
        var totalCount = 0
        var upCnt = 0
        var downCnt = 0
        var cash = budge

        for(i in 1 until stocks.size) {
            val stock = stocks[i]
            if(stocks[i] > stocks[i-1]) {       // 전의 주식보다 가격이 상승함
                upCnt++
                downCnt = 0
            }
            else if (stocks[i] < stocks[i-1]) { // 전의 주식보다 가격이 하락함
                upCnt = 0
                downCnt++
            }
            else {
                upCnt = 0
                downCnt = 0
            }

            // 젠부 사야할 떄
            if(downCnt >= 3) {
                if(cash >= stock) {    // 예산보다 주식이 더 싸면 (= 살 수 있으면)
                    val count = (cash / stock)
                    cash -= (count * stock)
                    totalCount += count
                }
            }

            // 젠부 팔아야 할 때
            if(upCnt >= 3 || i == 13) { // 마지막에 주식은 팔아야 하므로 i==13 조건 추가
                cash += (totalCount * stocks[i])
                upCnt = 0
                totalCount = 0
            }
        }
        return cash
    }
}

// Silver4 - 45min (Implementation)
class Problem2578 {
    private val bingoCheckMap = Array(5) { Array(5) {false} }
    private var bingoCnt = 0
    fun solve() {
        var ans = 0
        val bingoMap = Array(5) { Array(5) {""} }
        // 입력
        for(i in 0 until 5) {
            val input = readln().split(" ")
            for(j in input.indices) {
                bingoMap[i][j] = input[j]
            }
        }

        for(i in 0 until 5) {
            val input = readln().split(" ")
            for(number in input) {
                findNumber(number, bingoMap)
                ans++
                if(bingoCnt >= 3) {
                    println(ans)
                    return
                }
            }
        }


        // 빙고는 어떻게 체크할 것인가?
        // 특이사항: 입력을 다 받고 체크할 수 있음
        // 9번쨰부터 하나 둘 때마다 빙고체크를 한다. (단, 헀던 빙고는 넘어가는 로직이 필요함 -> 아니네 필요없네.)

        // 2차원 배열로 만든다. -> 빙고 체크하기 수월
        // 방문(체크)했는지 알 수 있는 변수가 필요하다. -> bool 2차원 배열을 만든다.
        // 상하좌우, 대각선을 체크하는 함수를 만든다. (하나라도 false가 있으면 바로 리턴


    }

    private fun findNumber(target: String, bingoMap: Array<Array<String>>) {
        for(i in bingoMap.indices) {
            for(j in bingoMap.indices) {
                if(bingoMap[i][j] == target) {
                    bingoCheckMap[i][j] = true
                    checkBingo(i, j)
                    return
                }
            }
        }
    }

    private fun checkBingo(row: Int, col: Int) {
        var cnt = 0
        // 좌우
        for(i in 0 until 5) {
            if(bingoCheckMap[row][i]) cnt++
        }
        if(cnt == 5) bingoCnt++
        cnt = 0

        // 상하
        for(i in 0 until 5) {
            if(bingoCheckMap[i][col]) cnt++
        }
        if(cnt == 5) bingoCnt++
        cnt = 0

        // 대각선
        if(row == col) {
            for(i in 0 until 5) {
                if(bingoCheckMap[i][i]) cnt++
            }
            if(cnt == 5) bingoCnt++
            cnt = 0
        }

        // 반대 대각선
        if(row == 4-col || col == 4-row) {
            for(i in 0 until 5) {
                if(bingoCheckMap[i][4-i]) cnt++
            }
            if(cnt == 5) bingoCnt++
        }
    }
}

// Silver4 - 1hour (Implementation) - 로직보다 출력 형식에서 실수해서 오래 걸림.
class Problem1244 {
    private lateinit var switchs: MutableList<String>
    fun solve() {
        // 입력
        val n = readln().toInt()    // 스위치 수
        switchs = readln().split(" ").toMutableList()
        val m = readln().toInt()    // 학생 수

        // 학생 수만큼 돌면서
        repeat(m) {
            val str = readln().split(" ")   // [0]=gender - [1]=number, ex) 1 3
            val number = str[1].toInt()
            // switch case로 남자 함수 / 여자 함수로 나눠서 바꿔줌.
            when (str[0]) {
                "1" -> man(number)
                "2" -> woman(number - 1) // index로 보내주기
            }
        }

        for(i in 0 until switchs.size) {
            if(i % 20 == 0) println()
            print(switchs[i] + " ")
        }
    }
    private fun man(number: Int) {
        // number의 배수를 바꿔준다. (단, index 처리(=-1) 해줘야 함.)
        val index = number - 1
        for(i in index until switchs.size step number) {
            if(switchs[i] == "0") switchs[i] = "1"
            else switchs[i] = "0"
        }
    }

    private fun woman(index: Int) {
        var downIndex = index
        var upIndex = index
        // 대칭으로 넓혀가면서 바꿔주기.
        // 대칭이 맞지 않으면 return
        // 단, index가 0보다 미만인지, size 이상인지 체크 먼저.
        while(true) {
            if(downIndex < 0 || upIndex >= switchs.size) return
            if(switchs[downIndex] != switchs[upIndex]) return

            // 반대로 바꿔주기
            if(switchs[downIndex] == "0") {
                switchs[upIndex] = "1"
                switchs[downIndex] = "1"
            } else {
                switchs[upIndex] = "0"
                switchs[downIndex] = "0"
            }

            downIndex -= 1
            upIndex += 1
        }
    }
}