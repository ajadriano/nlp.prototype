<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="VP[(VB|VBZ|VBP)[@lemma='have']][NP[count(QP)=0 and count(CD)=0]]">
ObjectSomeValuesFrom(
        <xsl:call-template name="noun_phrase_have_object_property">
            <xsl:with-param name="NP" select="NP" />
	</xsl:call-template>
   	<xsl:text> </xsl:text>
   	Thing())
</xsl:template>

<xsl:template match="VP[count(preceding-sibling::ADVP[RB])=0 and (VB|VBZ|VBP)[@lemma!='be' and @lemma!='have']][NP[count(RB[@lemma='only'])=0]]">
ObjectSomeValuesFrom(
    <xsl:call-template name="verb_to_object_property">
            <xsl:with-param name="verb" select="(VB|VBZ|VBP)" />
    </xsl:call-template>
    <xsl:text> </xsl:text>
    <xsl:apply-templates select="NP"/>)
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

<xsl:template match="NP[NP[NN|NNS] and PP[IN[@lemma='of'] and NP[NN|NNS]]]">
ObjectSomeValuesFrom(
        <xsl:call-template name="noun_to_object_property_of">
            <xsl:with-param name="noun" select="NP/(NN|NNS)" />
	</xsl:call-template>
   	<xsl:text> </xsl:text>
   	<xsl:apply-templates select="PP/NP"/>)
</xsl:template>

</xsl:stylesheet>