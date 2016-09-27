<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="S[count(S)=0][VP/(VB|VBZ|VBP|VBD)/@id=@root and NP[NNP|NNPS|PRP] and VP[NP[NNP|NNPS|PRP]]]">
ObjectPropertyAssertion(
	<xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/(VB|VBZ|VBP|VBD)" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="VP/NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(S)=0][NP[NNP|NNPS|PRP and *[@nsubj]] and VP[(VB|VBZ|VBP)[@cop and @lemma='be'] and count(RB[@neg])=0][NP[NP[NN|NNS] and PP[IN][NP[NNP|NNPS|PRP]]]]]">
ObjectPropertyAssertion(
        <xsl:call-template name="noun_phrase_of_object_property">
		<xsl:with-param name="NP" select="VP/NP/NP" />
	</xsl:call-template>
        <xsl:text> </xsl:text>
        <xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
        <xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="VP/NP/PP/NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(S)=0][NP[NNP|NNPS|PRP and *[@nsubj]] and VP[(VB|VBZ|VBP)[@cop and @lemma='be'] and count(RB[@neg])>0][NP[NP[NN|NNS] and PP[IN][NP[NNP|NNPS|PRP]]]]]">
NegativeObjectPropertyAssertion(
        <xsl:call-template name="noun_phrase_of_object_property">
		<xsl:with-param name="NP" select="VP/NP/NP" />
	</xsl:call-template>
        <xsl:text> </xsl:text>
        <xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
        <xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="VP/NP/PP/NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(S)=0][VP/(VB|VBZ|VBP)/@id=@root and NP[(NNP|NNPS|PRP)[@nsubj]] and VP[(VB|VBZ|VBP)[@lemma='have'] and NP[DT and *[@dobj]]]]">
ObjectPropertyAssertion(
	<xsl:call-template name="noun_phrase_have_object_property">
		<xsl:with-param name="NP" select="VP/NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
        AnonymousIndividual()
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>)
</xsl:template>

</xsl:stylesheet>