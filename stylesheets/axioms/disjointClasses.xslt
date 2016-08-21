<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="S[count(S)=0][NN[@lemma = 'nothing'] and VP[MD[@lemma = 'can'] and VP[VB[@lemma = 'be'] and NP[CC[position()=1 and @preconj] and CC[position()=2 and @lemma = 'and']]]]]">
DisjointClasses(
	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="VP/VP/NP/NP[position()=1]" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="VP/VP/NP/NP[position()=2]" />
	</xsl:call-template>)
</xsl:template>

</xsl:stylesheet>