
data class Philosopher(val id: Int, var hasFork: Boolean = false)

fun main() {

    while (true)
    {
        print("Укажите числом количество философов за круглым столом(не равное 0): ")
        val numPhilosophers = readln().toIntOrNull() ?: continue
        if (numPhilosophers < 1) {
            continue
        }

        val philosophers = ArrayList<Philosopher>()//создаем пустой массив ArrayList(коллекцию с порядком)
        for (i in 0 until numPhilosophers) {
            philosophers.add(Philosopher(i)) //добавляем в массив объекты типа Philosopher с id равным i
        }

        val forks = MutableList<Boolean>(numPhilosophers) { true } // создаём изменяемый список вилок, изначально все вилки свободны (true)


        val shuffledPhilosophers = philosophers.shuffled() //перемешиваем список философов в случайном порядке.

        for (philosopher in shuffledPhilosophers)
        {
            val leftForkIndex = philosopher.id // Индекс левой вилки от философа
            val rightForkIndex = (philosopher.id + 1) % numPhilosophers // Индекс правой вилки от философа

            val choiceFork = listOf(leftForkIndex, rightForkIndex).shuffled() //в списке содержащем индексы левой и правой вилок метод shuffled() перемешивает элементы списка в случайном порядке.

            for (forkIndex in choiceFork) {
                if (forks[forkIndex]) {
                    forks[forkIndex] = false
                    philosophers[philosopher.id] = philosophers[philosopher.id].copy(hasFork = true)
                    break // Выходим из внутреннего цикла после успешного взятия вилки
                }
            }
        }

        println("Результаты трапезы:")
        philosophers.forEach {
            if (it.hasFork) {
                println("Философ ${it.id} обедает.")
            } else {
                println("Философ ${it.id} размышляет.")
            }
        }

        print("Хотите запустить программу ещё раз?(Введите 1 для продолжения и что-угодно другое для выхода): ")

        if(readln()=="1") continue
        else break




    }
}
