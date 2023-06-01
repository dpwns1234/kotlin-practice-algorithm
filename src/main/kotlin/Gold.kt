// Gold5 - 1hour (Binary Search) 해답 봄
class Problem3079 {
    fun solve() {
        val str = readln().split(" ")
        val n = str[0].toInt()
        val m = str[1].toLong()
        val judgingList = mutableListOf<Int>()
        for(i in 0 until n) {
            judgingList.add(readln().toInt())
        }
        val answer = lowerBoundBS(judgingList, m)

        print(answer)
    }

    private fun lowerBoundBS(list: List<Int>, m: Long): Long {
        var st = 0L
        var en: Long = list.min() * m
        while(st < en) {
            val mid = (st + en) / 2
            val people = getAvailableBoardingPeople(list, mid)
            if(people >= m)  en = mid
            else st = mid+1
        }

        return en
    }

    private fun getAvailableBoardingPeople(list: List<Int>, sec: Long): Long { // 시간동안 최대한 탑승할 수 있는 사람의 수 리턴
        var sum = 0L
        for(e in list) {
            sum += (sec/e) // 주어진 시간 / 1명 심사하는데 걸리는 시간
        }

        return sum
    }
}