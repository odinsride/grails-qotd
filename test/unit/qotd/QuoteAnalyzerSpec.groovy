package qotd

import spock.lang.*

class QuoteAnalyzerSpec extends Specification {

	@Shared quotes = [
	    new Quote(author: "Peter Ledbrook",
				  content: "Time waits for no man"),
		new Quote(author: "Glen Smith",
				  content: "Groovy solves all problems")
	]

	@Unroll
	def "Total number of quotes"() {
		given: "An analyzer initialized with known quotes"
		def analyzer = new QuoteAnalyzer(inputQuotes)

		when: "I ask for the quote count"
		def quoteCount = analyzer.quoteCount

		then: "The number of quotes in the test list is returned"
		quoteCount == expected

		where:
		inputQuotes		|	expected
			[]			| 		0
		  quotes		| 		2
	}

	@Unroll
	def "Number of quotes per author"() {
		given: "An analyzer initialized with known quotes"
		def analyzer = new QuoteAnalyzer(quoteList)

		expect: "The per-author quote count is correct"
		expected == analyzer.quoteCountPerAuthor

		where:
		quoteList		| 	expected
		[]				|	[:]
		quotes			|	["Peter Ledbrook": 1, "Glen Smith": 1]
	}
}
