<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<!-- A entity_A is a [VP] -->
<xsl:template match="/ROOT/S[NP[DT[position() = 1 and @lemma = 'a']][*[@nsubj]] and VP[*[@cop and @lemma='be']]]">
EquivalentClasses(
	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="VP"/>)
</xsl:template>

</xsl:stylesheet>