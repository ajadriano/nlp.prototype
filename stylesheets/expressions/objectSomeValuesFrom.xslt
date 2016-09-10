<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="NP[NP/NN/@lemma='anyone' and SBAR[WHNP[*[@nsubj]]][S]]">
    <xsl:apply-templates select="SBAR/S"/>
</xsl:template>

<xsl:template match="NP[NP/NN/@lemma='everybody' and SBAR[WHNP[*[@nsubj]]][S]]">
    <xsl:apply-templates select="SBAR/S"/>
</xsl:template>

<xsl:template match="S[count(*)=1]/VP[(VB|VBZ)[@relcl][@lemma!='have']][NP]">
ObjectSomeValuesFrom(
        <xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VB|VBZ" />
	</xsl:call-template>
   	<xsl:text> </xsl:text>
   	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(*)=1]/VP[(VB|VBZ)[@lemma='have']][NP]">
ObjectSomeValuesFrom(
        <xsl:call-template name="noun_phrase_have_object_property">
		<xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
   	<xsl:text> </xsl:text>
   	Thing())
</xsl:template>

<xsl:template match="S[count(*)=1]/VP[MD[@aux] and VP[(VB|VBZ)[@relcl]][NP]]">
ObjectSomeValuesFrom(
    <xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/(VB|VBZ)" />
	</xsl:call-template>
   	<xsl:text> </xsl:text>
   	<xsl:call-template name="noun_phrase_to_class">
		<xsl:with-param name="NP" select="VP/NP" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(*)=1]/VP[(VB|VBZ)[@relcl]][NN|NNS]">
ObjectSomeValuesFrom(
    <xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP|VBZ" />
	</xsl:call-template>
   	<xsl:text> </xsl:text>
   	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="NN|NNS" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="S[count(*)=1]/VP[MD[@aux] and VP[(VB|VBZ)[@relcl]][NN|NNS]]">
ObjectSomeValuesFrom(
    <xsl:call-template name="verb_to_object_property">
		<xsl:with-param name="verb" select="VP/(VB|VBZ)" />
	</xsl:call-template>
   	<xsl:text> </xsl:text>
   	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="VP/(NN|NNS)" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="NP[NP[POS and *[@poss]] and NN|NNS]">
ObjectSomeValuesFrom(
        <xsl:call-template name="noun_to_object_property_of">
            <xsl:with-param name="noun" select="NN|NNS" />
	</xsl:call-template>
   	<xsl:text> </xsl:text>
   	<xsl:call-template name="noun_to_class">
		<xsl:with-param name="noun" select="NP/*[@poss]" />
	</xsl:call-template>)
</xsl:template>

<xsl:template match="NP[NP[DT and (NN|NNS)] and PP[IN[@lemma='of'] and NP[DT[@lemma='a'] and (NN|NNS)]]]">
ObjectSomeValuesFrom(
        <xsl:call-template name="noun_to_object_property_of">
            <xsl:with-param name="noun" select="NP/(NN|NNS)" />
	</xsl:call-template>
   	<xsl:text> </xsl:text>
   	<xsl:call-template name="noun_phrase_to_class">
            <xsl:with-param name="NP" select="PP/NP" />
	</xsl:call-template>)
</xsl:template>

</xsl:stylesheet>