<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="S[count(S)=0][VP/VBZ/@id=@root and NP[NNP|NNPS] and VP[NP[NNP|NNPS]]]">
ObjectPropertyAssertion(
	<xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/VBZ" />
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

<xsl:template match="S[count(S)=0][VP/VBZ/@id=@root and NNP|NNPS and VP[NNP|NNPS]]">
ObjectPropertyAssertion(
	<xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/VBZ" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_individual">
		<xsl:with-param name="noun" select="NNP|NNPS" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_individual">
		<xsl:with-param name="noun" select="VP/NNP|NNPS" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(S)=0][VP/VBZ/@id=@root and NNP|NNPS and VP[NP[NNP|NNPS]]]">
ObjectPropertyAssertion(
	<xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/VBZ" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_individual">
		<xsl:with-param name="noun" select="NNP|NNPS" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="VP/NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(S)=0][VP/VBZ/@id=@root and NP[NNP|NNPS] and VP[NNP|NNPS]]">
ObjectPropertyAssertion(
	<xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/VBZ" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_individual">
		<xsl:with-param name="noun" select="VP/NNP|NNPS" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(S)=0][NP[NNP|NNPS and *[@nsubj]] and VP[VBZ[@cop and @lemma='be']][NP[NP[*[position()=1 and @lemma='the']] and PP[IN][NP[NNP|NNPS]]]]]">
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

<xsl:template match="S[count(S)=0][NP[NNP|NNPS and *[@nsubj]] and VP[VBZ[@cop and @lemma='be']][NP[NP[*[position()=1 and @lemma='the']] and PP[IN][NNP|NNPS]]]]">
ObjectPropertyAssertion(
        <xsl:call-template name="noun_phrase_of_object_property">
		<xsl:with-param name="NP" select="VP/NP/NP" />
	</xsl:call-template>
        <xsl:text> </xsl:text>
        <xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>	
        <xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_individual">
		<xsl:with-param name="noun" select="VP/NP/PP/NNP|NNPS" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(S)=0][NNP|NNPS[@nsubj] and VP[VBZ[@cop and @lemma='be']][NP[NP[*[position()=1 and @lemma='the']] and PP[IN][NP[NNP|NNPS]]]]]">
ObjectPropertyAssertion(
        <xsl:call-template name="noun_phrase_of_object_property">
		<xsl:with-param name="NP" select="VP/NP/NP" />
	</xsl:call-template>
        <xsl:text> </xsl:text>
        <xsl:call-template name="noun_to_individual">
		<xsl:with-param name="noun" select="NNP|NNPS" />
	</xsl:call-template>
        <xsl:text> </xsl:text>
	<xsl:call-template name="noun_phrase_to_individual">
		<xsl:with-param name="NP" select="VP/NP/PP/NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(S)=0][NNP|NNPS[@nsubj] and VP[VBZ[@cop and @lemma='be']][NP[NP[*[position()=1 and @lemma='the']] and PP[IN][NNP|NNPS]]]]">
ObjectPropertyAssertion(
        <xsl:call-template name="noun_phrase_of_object_property">
		<xsl:with-param name="NP" select="VP/NP/NP" />
	</xsl:call-template>
        <xsl:text> </xsl:text>
        <xsl:call-template name="noun_to_individual">
		<xsl:with-param name="noun" select="NNP|NNPS" />
	</xsl:call-template>
        <xsl:text> </xsl:text>
	<xsl:call-template name="noun_to_individual">
		<xsl:with-param name="noun" select="VP/NP/PP/NNP|NNPS" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(S)=0][VP/VBZ/@id=@root and NP[(NNP|NNPS)[@nsubj]] and VP[VBZ[@lemma='have'] and NP[DT and *[@dobj]]]]">
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