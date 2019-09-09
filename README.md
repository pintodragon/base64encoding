This project uses ScalaTest and was built using Scala 2.13 locally. It may run with otoher versions of scala but they have not been tested.

The `Base64.scala` object has a main method that will take a single argument, validate it, and print out the results.

To compile the project run: `sbt compile`

To run the project run: `sbt "run <argument>"` *NOTE* The quoates aorund the `run <argument>`, if you forget the quotes, sbt will throw an error that it expected a `;`.

To run the ScalaTests run: `sbt test`
