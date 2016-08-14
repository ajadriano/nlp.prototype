<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:include href="iri.xslt"/>

<xsl:template match="/ROOT/*" priority="0">
	Unknown(
	<xsl:for-each select=".//*[@id]">
		<xsl:value-of select="."/><xsl:text> </xsl:text>
    </xsl:for-each>)
</xsl:template>

<!-- A entity_A is a [VP] -->
<xsl:template match="/ROOT/S[NP[DT[position() = 1 and @lemma = 'a']][*[@nsubj]] and VP[*[@cop and @lemma='be']]]">
EquivalentClasses(
	<xsl:call-template name="noun_phrase_to_iri">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="VP"/>)
</xsl:template>

<xsl:template match="/ROOT/S[NP[DT[position() = 1 and @lemma = 'all']][*[@nsubj]] and VP[VBP[position()=1 and @cop]][NNS]]">
SubClassOf(
	<xsl:call-template name="noun_phrase_to_iri">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="word_to_iri">
		<xsl:with-param name="text" select="VP/NNS/@lemma" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="/ROOT/S[NNS[@nsubj] and VP[VBP[position()=1 and @cop]][NNS]]">
SubClassOf(
	<xsl:call-template name="word_to_iri">
		<xsl:with-param name="text" select="NNS/@lemma" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="word_to_iri">
		<xsl:with-param name="text" select="VP/NNS/@lemma" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="/ROOT/S[NN[@lemma = 'nothing'] and VP[MD[@lemma = 'can'] and VP[VB[@lemma = 'be'] and NP[CC[position()=1 and @preconj] and CC[position()=2 and @lemma = 'and']]]]]">
DisjointClasses(
	<xsl:call-template name="noun_phrase_to_iri">
		<xsl:with-param name="NP" select="VP/VP/NP/NP[position()=1]" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_iri">
		<xsl:with-param name="NP" select="VP/VP/NP/NP[position()=2]" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="/ROOT/SQ[VBZ[@lemma='be']][NP[position()=1]][NP[position()=2]]">
IsDirectSubClassOf(
	<xsl:call-template name="noun_phrase_to_iri">
		<xsl:with-param name="NP" select="NP[position()=1]" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_iri">
		<xsl:with-param name="NP" select="NP[position()=2]" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="VP[VBZ[@cop]][NP[count(SBAR)=0]]">
   <xsl:call-template name="noun_phrase_to_iri">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
</xsl:template>

<xsl:template match="VP[VBZ[@cop]][NP][count(*)=2]/NP[SBAR[WDT[position() = 1]][S[count(*)=1]/VP[position() = 1]]]">
ObjectIntersectionOf(
	<xsl:call-template name="noun_phrase_to_iri">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:apply-templates select="SBAR/S/VP"/>)
</xsl:template>

<xsl:template match="VP[VBZ[@relcl]][NP]">
   ObjectSomeValuesFrom(
   	<xsl:value-of select="VBZ/@lemma"/>
   	<xsl:text> </xsl:text>
   	<xsl:call-template name="noun_phrase_to_iri">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="VP[VBZ[@relcl]][NP[count(*)=2][QP[position()=1]][NNS]]">
   ObjectMinCardinality(<xsl:value-of select="NP/QP/CD"/><xsl:text> </xsl:text> <xsl:value-of select="VBZ"/><xsl:value-of select="concat(upper-case(substring(NP/NNS/@lemma,1,1)), substring(NP/NNS/@lemma, 2))"/>)
</xsl:template>
 
<xsl:template match="text()|@*"/>

</xsl:stylesheet>